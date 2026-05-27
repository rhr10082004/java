import { useEffect, useState } from "react";
import { StatCard } from "../components/StatCard";
import { useAuth } from "../context/AuthContext";
import { getDashboardSummary } from "../services/api";

export function DashboardPage() {
  const { token } = useAuth();
  const [summary, setSummary] = useState(null);
  const [error, setError] = useState("");

  useEffect(() => {
    async function load() {
      try {
        const data = await getDashboardSummary(token);
        setSummary(data);
      } catch (err) {
        setError(err.message || "Failed to load dashboard");
      }
    }
    if (token) load();
  }, [token]);

  const recent = summary?.recentTransactions || [];

  return (
    <section className="space-y-6">
      <div className="grid md:grid-cols-4 gap-4">
        <StatCard title="Total Expenses" value={`Rs ${summary?.monthlyTotal ?? "0.00"}`} />
        <StatCard title="Remaining Budget" value={`Rs ${summary?.remainingBudget ?? "0.00"}`} tone="text-app-success" />
        <StatCard title="Top Category" value={summary?.topCategory ?? "N/A"} />
        <StatCard title="Monthly Prediction" value={`Rs ${summary?.predictedMonthlySpend ?? "0.00"}`} tone="text-app-warning" />
      </div>
      <div className="grid lg:grid-cols-2 gap-4">
        <div className="rounded-xl bg-app-card p-5 h-64 shadow-soft overflow-auto">
          <h3 className="font-semibold mb-3">Category Totals</h3>
          {(summary?.categoryTotals || []).map((c) => (
            <div key={c.category} className="flex justify-between text-sm py-1 border-b border-slate-700/40">
              <span>{c.category}</span>
              <span>Rs {c.total}</span>
            </div>
          ))}
        </div>
        <div className="rounded-xl bg-app-card p-5 h-64 shadow-soft overflow-auto">
          <h3 className="font-semibold mb-3">7-Day Trend</h3>
          {(summary?.recentDailyTrend || []).map((d) => (
            <div key={d.date} className="flex justify-between text-sm py-1 border-b border-slate-700/40">
              <span>{d.date}</span>
              <span>Rs {d.total}</span>
            </div>
          ))}
        </div>
      </div>
      {error ? <p className="text-app-danger text-sm">{error}</p> : null}
      <div className="rounded-xl bg-app-card p-5 shadow-soft">
        <h2 className="font-bold mb-4">Recent Transactions</h2>
        <div className="overflow-auto">
          <table className="w-full text-sm">
            <thead>
              <tr className="text-app-muted text-left">
                <th className="pb-2">Category</th>
                <th className="pb-2">Amount</th>
                <th className="pb-2">Date</th>
                <th className="pb-2">Method</th>
              </tr>
            </thead>
            <tbody>
              {recent.map((row) => (
                <tr key={row.date + row.amount} className="border-t border-slate-700/70">
                  <td className="py-2">{row.category}</td>
                  <td className="py-2">Rs {row.amount}</td>
                  <td className="py-2">{row.transactionDate || row.date}</td>
                  <td className="py-2">{row.paymentMethod || row.method}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </section>
  );
}
