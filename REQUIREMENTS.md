# PennyWise AI - Local Setup Requirements

This file lists everything needed to run the project on a local terminal (Windows PowerShell).

## 1) Software Requirements

Install these tools first:

1. `Git` (latest)
2. `Java JDK 21+` (project runs with newer JDKs too)
3. `Apache Maven 3.9+`
4. `Node.js 18+` with `npm`
5. `PostgreSQL 14+`

Optional:

1. `Docker Desktop` (if you want containerized run later)

## 2) Clone / Open Project

```powershell
cd D:\JAVA
git clone https://github.com/rhr10082004/java.git finance
cd D:\JAVA\finance
```

If repo already exists locally:

```powershell
cd D:\JAVA\finance
```

## 3) Database Requirement

Create PostgreSQL database:

```sql
CREATE DATABASE pennywise;
```

## 4) Backend Environment Variables

Set these in PowerShell before running backend:

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/pennywise"
$env:DB_USER="postgres"
$env:DB_PASSWORD="postgres"
$env:JWT_SECRET="replace-with-32-plus-char-secret-key"
$env:JWT_EXPIRATION_MS="86400000"
```

## 5) Backend Build and Run

If `mvn` is installed globally:

```powershell
cd D:\JAVA\finance\backend
mvn clean package -DskipTests
java -jar .\target\finance-0.0.1-SNAPSHOT.jar
```

If global Maven is not installed, use local Maven binary:

```powershell
cd D:\JAVA\finance\backend
& "D:\JAVA\finance\tools\apache-maven-3.9.9\bin\mvn.cmd" clean package -DskipTests
java -jar .\target\finance-0.0.1-SNAPSHOT.jar
```

Backend runs at:

1. `http://localhost:8080`
2. Health API: `http://localhost:8080/api/health`

## 6) Frontend Install and Run

```powershell
cd D:\JAVA\finance\frontend
& "C:\Program Files\nodejs\npm.cmd" install
& "C:\Program Files\nodejs\npm.cmd" run dev
```

Frontend runs at:

1. `http://127.0.0.1:5173`

## 7) Quick Run Order

1. Start PostgreSQL
2. Start backend (port `8080`)
3. Start frontend (port `5173`)
4. Open frontend URL in browser

## 8) Common Errors and Fixes

1. Error: `mvn is not recognized`
   Fix: install Maven or run local Maven path from Section 5.
2. Error in PowerShell with npm path:
   Fix: use `& "C:\Program Files\nodejs\npm.cmd" run dev`
3. DB connection failure:
   Fix: verify PostgreSQL is running and env vars are correct.
