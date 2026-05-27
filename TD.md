# TD - Technical Dictionary (PennyWise AI)

## Authentication and Security
- **JWT (JSON Web Token):** Token used to verify logged-in user identity without server-side session storage.
- **Bearer Token:** JWT sent in HTTP `Authorization` header as `Bearer <token>`.
- **Stateless Auth:** Server does not store session; every request carries auth token.
- **BCrypt:** Password hashing algorithm used before storing passwords.
- **Security Filter Chain:** Spring Security pipeline that validates/authorizes incoming requests.

## Backend Architecture
- **Controller:** API entry point (`/api/...`) handling request/response.
- **Service Layer:** Business logic (validation, computations, decision rules).
- **Repository Layer:** DB access through Spring Data JPA.
- **DTO (Data Transfer Object):** Structured request/response model exposed by API.
- **Entity:** Java class mapped to DB table via JPA annotations.
- **ORM (Object-Relational Mapping):** Mapping Java objects to relational tables.

## Data and Query Concepts
- **CRUD:** Create, Read, Update, Delete operations.
- **Pagination:** Splitting large results into pages (`Pageable`).
- **Aggregation Query:** Query that calculates totals (`SUM`) and grouped data.
- **Deduplication:** Preventing duplicate insights using existence checks and time window.

## Frontend Concepts
- **SPA (Single Page Application):** React app where routing happens client-side.
- **Protected Route:** Route accessible only when user is authenticated.
- **Context API:** Global state holder for auth token/user data.
- **LocalStorage:** Browser storage used for token/session persistence.
- **Responsive UI:** Layout adapts for desktop/mobile screens.

## Platform and Tooling
- **Vite:** Frontend build/dev tool with fast local server.
- **Tailwind CSS:** Utility-first CSS framework.
- **Recharts:** Charting library used for analytics graphs.
- **Maven:** Java build and dependency tool.
- **Docker Compose:** Multi-container orchestration for app + services.
- **Flyway:** DB migration tool (migration file exists in project).
