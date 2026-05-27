# PennyWise AI - How To Run and Use

## 1) Prerequisites

- Java 21+ installed
- Maven 3.9+ installed and available as `mvn`
- PostgreSQL running locally
- Node.js + npm installed

## 2) Database Setup

Create database:

```sql
create database pennywise;
```

Set backend env vars (PowerShell example):

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/pennywise"
$env:DB_USER="postgres"
$env:DB_PASSWORD="postgres"
$env:JWT_SECRET="replace-with-32-plus-char-secret-key"
$env:JWT_EXPIRATION_MS="86400000"
```

## 3) Run Backend (Spring Boot)

```powershell
cd D:\JAVA\finance\backend
mvn spring-boot:run
```

Backend URL:

- `http://localhost:8080`
- Health check: `http://localhost:8080/api/health`

## 4) Run Frontend (React + Vite)

```powershell
cd D:\JAVA\finance\frontend
& "C:\Program Files\nodejs\npm.cmd" install
& "C:\Program Files\nodejs\npm.cmd" run dev
```

Frontend URL:

- `http://127.0.0.1:5173`

## 5) How To Use the Website

1. Open `http://127.0.0.1:5173`
2. Click `Get Started`
3. Register with `name, email, password`
4. You will be redirected to Dashboard
5. Use sidebar to view:
   - Dashboard
   - Transactions
   - Budgets
   - Analytics
6. Click `Logout` from top-right to clear local session

## 6) Implemented Till Phase 3

- Phase 0: scope + architecture foundation
- Phase 1: frontend UI foundation
- Phase 2: backend foundation (Spring security, Flyway, JPA, PostgreSQL config)
- Phase 3: auth system (register, login, JWT, protected routes)

## 6.1) Implemented Remaining Phases (MVP+)

- Transactions: full CRUD
- Budgets: create/list/update/delete with month/category rules
- Dashboard analytics:
  - monthly totals
  - category totals
  - weekly trend
  - top category
  - monthly spend prediction
- AI Insights:
  - list insights
  - generate insights from current-vs-last-month spending
- OCR (lightweight MVP):
  - `POST /api/receipt/upload` to parse text-like receipts and autofill fields

## 7) Current APIs

- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/auth/me` (requires Bearer token)
- `GET /api/health`
- `GET /api/dashboard/summary`
- `GET /api/transactions`
- `POST /api/transactions`
- `PUT /api/transactions/{id}`
- `DELETE /api/transactions/{id}`
- `GET /api/budgets?budgetMonth=YYYY-MM`
- `POST /api/budgets`
- `PUT /api/budgets/{id}`
- `DELETE /api/budgets/{id}`
- `GET /api/insights`
- `POST /api/insights/generate`
- `POST /api/receipt/upload`
