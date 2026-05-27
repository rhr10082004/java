import { Link } from "react-router-dom";

export function LandingPage() {
  return (
    <main className="min-h-screen bg-app-bg text-app-text">
      <section className="max-w-6xl mx-auto px-6 py-20">
        <h1 className="text-4xl md:text-6xl font-extrabold max-w-3xl">
          Understand your money with real intelligence, not just transaction logs.
        </h1>
        <p className="text-app-muted mt-6 max-w-2xl">
          PennyWise AI helps you track expenses, monitor budgets, and surface actionable insights.
        </p>
        <div className="mt-8 flex gap-3">
          <Link to="/register" className="bg-app-primary px-5 py-3 rounded-lg font-semibold">
            Get Started
          </Link>
          <Link to="/login" className="border border-slate-600 px-5 py-3 rounded-lg text-app-muted">
            Login
          </Link>
        </div>
      </section>
    </main>
  );
}
