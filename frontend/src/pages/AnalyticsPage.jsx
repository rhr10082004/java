import { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import { generateInsights, getDashboardSummary, getInsights } from "../services/api";
import {
  Bar,
  BarChart,
  CartesianGrid,
  Line,
  LineChart,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";

export function AnalyticsPage() {
  const { token } = useAuth();
  const [insights, setInsights] = useState([]);
  const [summary, setSummary] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  async function load() {
    try {
      const [data, dashboard] = await Promise.all([
        getInsights(token, 0, 20),
        getDashboardSummary(token),
      ]);
      setInsights(data.content || []);
      setSummary(dashboard);
    } catch (err) {
      setError(err.message || "Failed to load insights");
    }
  }

  useEffect(() => {
    if (token) load();
  }, [token]);

  async function handleGenerate() {
    setLoading(true);
    setError("");
    try {
      await generateInsights(token);
      await load();
    } catch (err) {
      setError(err.message || "Failed to generate insights");
    } finally {
      setLoading(false);
    }
  }

  return (
    <section className="space-y-4">
      <h1 className="text-2xl font-bold">Analytics</h1>
      <button onClick={handleGenerate} disabled={loading} className="bg-app-primary rounded-lg px-4 py-2 font-semibold disabled:opacity-70">
        {loading ? "Generating..." : "Generate Insights"}
      </button>
      <div className="grid lg:grid-cols-2 gap-4">
        <div className="rounded-xl bg-app-card p-5 h-64 shadow-soft">
          <h3 className="font-semibold mb-3">7-Day Spend Trend</h3>
          <div className="h-48">
            <ResponsiveContainer width="100%" height="100%">
              <LineChart data={summary?.recentDailyTrend || []}>
                <CartesianGrid strokeDasharray="3 3" stroke="#334155" />
                <XAxis dataKey="date" stroke="#94A3B8" />
                <YAxis stroke="#94A3B8" />
                <Tooltip />
                <Line type="monotone" dataKey="total" stroke="#3B82F6" strokeWidth={2} />
              </LineChart>
            </ResponsiveContainer>
          </div>
        </div>
        <div className="rounded-xl bg-app-card p-5 h-64 shadow-soft">
          <h3 className="font-semibold mb-3">Category Comparison</h3>
          <div className="h-48">
            <ResponsiveContainer width="100%" height="100%">
              <BarChart data={summary?.categoryTotals || []}>
                <CartesianGrid strokeDasharray="3 3" stroke="#334155" />
                <XAxis dataKey="category" stroke="#94A3B8" />
                <YAxis stroke="#94A3B8" />
                <Tooltip />
                <Bar dataKey="total" fill="#22C55E" radius={[6, 6, 0, 0]} />
              </BarChart>
            </ResponsiveContainer>
          </div>
        </div>
      </div>
      {error ? <p className="text-app-danger text-sm">{error}</p> : null}
      <div className="rounded-xl bg-app-card p-5 shadow-soft">
        <h2 className="font-bold mb-3">AI Insights</h2>
        <div className="space-y-2">
          {insights.length === 0 ? <p className="text-app-muted text-sm">No insights yet. Generate now.</p> : null}
          {insights.map((insight) => (
            <p key={insight.id} className="p-3 rounded-lg bg-slate-800 text-sm">{insight.message}</p>
          ))}
        </div>
      </div>
    </section>
  );
}
