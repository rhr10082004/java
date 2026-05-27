import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { useAuth } from "../context/AuthContext";

export function LoginPage() {
  const { login } = useAuth();
  const navigate = useNavigate();
  const [form, setForm] = useState({ email: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  async function handleSubmit(event) {
    event.preventDefault();
    setLoading(true);
    setError("");
    try {
      await login(form.email, form.password);
      navigate("/dashboard");
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="w-full max-w-md bg-app-card p-8 rounded-xl shadow-soft">
      <h1 className="text-2xl font-bold">Welcome Back</h1>
      <form className="mt-6 space-y-4" onSubmit={handleSubmit}>
        <input className="w-full bg-slate-800 rounded-lg p-3 border border-slate-700" placeholder="Email" value={form.email} onChange={(e) => setForm((f) => ({ ...f, email: e.target.value }))} />
        <input className="w-full bg-slate-800 rounded-lg p-3 border border-slate-700" type="password" placeholder="Password" value={form.password} onChange={(e) => setForm((f) => ({ ...f, password: e.target.value }))} />
        {error ? <p className="text-app-danger text-sm">{error}</p> : null}
        <button disabled={loading} type="submit" className="w-full bg-app-primary rounded-lg p-3 font-semibold disabled:opacity-70">{loading ? "Logging in..." : "Login"}</button>
      </form>
      <p className="text-app-muted text-sm mt-4">
        New user? <Link className="text-app-primary" to="/register">Create account</Link>
      </p>
    </div>
  );
}
