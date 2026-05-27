/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,jsx}"],
  theme: {
    extend: {
      colors: {
        app: {
          bg: "#0F172A",
          card: "#1E293B",
          primary: "#3B82F6",
          success: "#22C55E",
          warning: "#F59E0B",
          danger: "#EF4444",
          text: "#F8FAFC",
          muted: "#94A3B8",
        },
      },
      boxShadow: {
        soft: "0 8px 20px rgba(15, 23, 42, 0.35)",
      },
    },
  },
  plugins: [],
};
