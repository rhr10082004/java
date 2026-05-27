import { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import { createBudget, getBudgets } from "../services/api";

function barClass(percent) {
  if (percent > 100) return "bg-app-danger";
  if (percent > 80) return "bg-app-warning";
  return "bg-app-success";
}

export function BudgetsPage() {
  const { token } = useAuth();
  const [rows, setRows] = useState([]);
  const [month, setMonth] = useState(new Date().toISOString().slice(0, 7));
  const [error, setError] = useState("");
  const [form, setForm] = useState({ category: "Food", limitAmount: "" });

  useEffect(() => {
    async function load() {
      try {
        const data = await getBudgets(token, month);
        setRows(data);
      } catch (err) {
        setError(err.message || "Failed to load budgets");
      }
    }
    if (token) load();
  }, [token, month]);

  async function handleCreate(e) {
    e.preventDefault();
    setError("");
    try {
      await createBudget(token, {
        category: form.category,
        budgetMonth: month,
        limitAmount: parseFloat(form.limitAmount),
      });
      const data = await getBudgets(token, month);
      setRows(data);
      setForm((f) => ({ ...f, limitAmount: "" }));
    } catch (err) {
      setError(err.message || "Failed to create budget");
    }
  }

  return (
    <section className="space-y-4">
      <h1 className="text-2xl font-bold">Budgets</h1>
      <div className="rounded-xl bg-app-card p-5 shadow-soft">
        <form onSubmit={handleCreate} className="grid md:grid-cols-4 gap-3">
          <input type="month" value={month} onChange={(e) => setMonth(e.target.value)} className="bg-slate-800 rounded-lg p-3 border border-slate-700" />
          <select value={form.category} onChange={(e) => setForm((f) => ({ ...f, category: e.target.value }))} className="bg-slate-800 rounded-lg p-3 border border-slate-700">
            {["Food","Shopping","Travel","Bills","Entertainment","Health","Education","Other"].map((c) => <option key={c}>{c}</option>)}
          </select>
          <input placeholder="Limit Amount" value={form.limitAmount} onChange={(e) => setForm((f) => ({ ...f, limitAmount: e.target.value }))} className="bg-slate-800 rounded-lg p-3 border border-slate-700" />
          <button className="bg-app-primary rounded-lg p-3 font-semibold">Set Budget</button>
        </form>
        {error ? <p className="text-app-danger text-sm mt-3">{error}</p> : null}
      </div>
      <div className="grid gap-4">
        {rows.map((row) => {
          const limit = Number(row.limitAmount || 0);
          const spent = Number(row.spent || 0);
          const percent = limit > 0 ? Math.round((spent / limit) * 100) : 0;
          return (
            <div key={row.category} className="rounded-xl bg-app-card p-5 shadow-soft">
              <div className="flex justify-between">
                <p className="font-semibold">{row.category}</p>
                <p className="text-app-muted">Rs {spent} / Rs {limit}</p>
              </div>
              <div className="mt-3 h-2 bg-slate-700 rounded-full overflow-hidden">
                <div className={`h-full ${barClass(percent)}`} style={{ width: `${Math.min(percent, 100)}%` }} />
              </div>
            </div>
          );
        })}
      </div>
    </section>
  );
}
