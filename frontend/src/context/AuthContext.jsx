import { createContext, useContext, useEffect, useMemo, useState } from "react";
import { apiRequest } from "../services/api";

const AuthContext = createContext(null);
const TOKEN_KEY = "pennywise_token";
const USER_KEY = "pennywise_user";

export function AuthProvider({ children }) {
  const [token, setToken] = useState(localStorage.getItem(TOKEN_KEY));
  const [user, setUser] = useState(() => {
    const raw = localStorage.getItem(USER_KEY);
    return raw ? JSON.parse(raw) : null;
  });

  async function login(email, password) {
    const data = await apiRequest("/auth/login", {
      method: "POST",
      body: JSON.stringify({ email, password }),
    });
    persistAuth(data);
  }

  async function register(name, email, password) {
    const data = await apiRequest("/auth/register", {
      method: "POST",
      body: JSON.stringify({ name, email, password }),
    });
    persistAuth(data);
  }

  function persistAuth(data) {
    const nextUser = { name: data.name, email: data.email };
    setToken(data.token);
    setUser(nextUser);
    localStorage.setItem(TOKEN_KEY, data.token);
    localStorage.setItem(USER_KEY, JSON.stringify(nextUser));
  }

  function logout() {
    setToken(null);
    setUser(null);
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(USER_KEY);
  }

  useEffect(() => {
    function handleInvalidAuth() {
      logout();
    }
    window.addEventListener("pennywise:auth-invalid", handleInvalidAuth);
    return () => window.removeEventListener("pennywise:auth-invalid", handleInvalidAuth);
  }, []);

  useEffect(() => {
    async function validateSession() {
      if (!token) return;
      const validatingToken = token;
      try {
        await apiRequest("/auth/me", { method: "GET" }, validatingToken);
      } catch (_) {
        // Prevent stale validation responses from logging out a freshly logged-in user.
        if (localStorage.getItem(TOKEN_KEY) === validatingToken) {
          logout();
        }
      }
    }
    validateSession();
  }, [token]);

  const value = useMemo(
    () => ({ token, user, isAuthenticated: Boolean(token), login, register, logout }),
    [token, user]
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const ctx = useContext(AuthContext);
  if (!ctx) {
    throw new Error("useAuth must be used within AuthProvider");
  }
  return ctx;
}
