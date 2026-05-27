import { NavLink } from "react-router-dom";

const links = [
  { to: "/dashboard", label: "Dashboard" },
  { to: "/transactions", label: "Transactions" },
  { to: "/budgets", label: "Budgets" },
  { to: "/analytics", label: "Analytics" },
];

export function Sidebar() {
  return (
    <aside className="w-64 bg-app-card border-r border-slate-700 hidden md:block">
      <div className="p-6 text-xl font-bold">PennyWise AI</div>
      <nav className="px-3">
        {links.map((link) => (
          <NavLink
            key={link.to}
            to={link.to}
            className={({ isActive }) =>
              `block rounded-lg px-4 py-3 text-sm mb-1 ${isActive ? "bg-app-primary text-white" : "text-app-muted hover:bg-slate-700/40"}`
            }
          >
            {link.label}
          </NavLink>
        ))}
      </nav>
    </aside>
  );
}
