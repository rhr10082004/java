import { Outlet } from "react-router-dom";

export function AuthLayout() {
  return (
    <main className="min-h-screen bg-app-bg flex items-center justify-center p-4">
      <Outlet />
    </main>
  );
}
