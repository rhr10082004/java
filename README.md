# PennyWise AI

PennyWise AI is a full-stack personal finance project with a Spring Boot backend and React frontend.

## Tech Stack

1. Backend: Java, Spring Boot, Spring Security, JPA, Flyway
2. Database: PostgreSQL
3. Frontend: React, Vite, Tailwind CSS

## Project Structure

1. `backend` - REST APIs, auth, business logic, migrations
2. `frontend` - UI, routes, dashboards, auth screens
3. `docs` - planning and workflow notes
4. `REQUIREMENTS.md` - complete local setup and run instructions

## Main Features

1. User registration and login (JWT auth)
2. Transaction management
3. Budget management
4. Dashboard analytics and insights
5. Receipt OCR endpoint (MVP level)

## Run Locally

Use the full setup guide:

`REQUIREMENTS.md`

Quick start:

```powershell
# Backend
cd D:\JAVA\finance\backend
mvn clean package -DskipTests
java -jar .\target\finance-0.0.1-SNAPSHOT.jar

# Frontend (new terminal)
cd D:\JAVA\finance\frontend
& "C:\Program Files\nodejs\npm.cmd" install
& "C:\Program Files\nodejs\npm.cmd" run dev
```
