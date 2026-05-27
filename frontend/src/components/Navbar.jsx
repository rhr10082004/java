import { useAuth } from "../context/AuthContext";

export function Navbar() {
  const { user, logout } = useAuth();

  return (
    <header className="h-16 border-b border-slate-700 bg-app-card/60 backdrop-blur px-6 flex items-center justify-between">
      <div className="text-sm text-app-muted">Track smarter, spend wiser.</div>
      <div className="flex items-center gap-3">
        <button onClick={logout} className="text-xs px-3 py-2 rounded-md border border-slate-600 text-app-muted hover:text-app-text">
          Logout
        </button>
        <div className="h-9 w-9 rounded-full bg-app-primary text-white grid place-items-center font-semibold">
          {(user?.name || "U").slice(0, 1).toUpperCase()}
        </div>
      </div>
    </header>
  );
}
