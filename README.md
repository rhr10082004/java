# PennyWise AI

Learning-focused fullstack finance intelligence MVP.

## Structure

- `backend` - Spring Boot API
- `frontend` - React + Tailwind UI
- `docs` - phase planning and API/schema notes

## Phase Status

- Phase 0: completed
- Phase 1: completed

## Run (after installing toolchain locally)

Backend:

1. Install JDK 21+ and Maven 3.9+
2. Create PostgreSQL DB `pennywise`
3. Copy `backend/.env.example` values into environment variables or `application-local.yml`
4. Run:

```bash
cd backend
mvn spring-boot:run
```

Frontend:

1. Install Node.js LTS + npm
2. Run:

```bash
cd frontend
npm install
npm run dev
```
