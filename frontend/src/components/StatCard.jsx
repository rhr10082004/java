export function StatCard({ title, value, tone = "text-app-text" }) {
  return (
    <div className="rounded-xl bg-app-card p-4 shadow-soft">
      <p className="text-app-muted text-sm">{title}</p>
      <p className={`text-2xl font-extrabold mt-2 ${tone}`}>{value}</p>
    </div>
  );
}
