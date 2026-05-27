# PennyWise AI - Learning Notes (Resume Ready)

## 1) Project Name and One-Line Pitch
**Project Name:** PennyWise AI  
**Pitch:** A full-stack personal finance tracker that helps users log expenses, manage monthly budgets, and generate spending insights with secure JWT-based authentication.

## 2) What Idea Is Behind This Project?
The idea is to move beyond plain expense logging and provide **actionable financial awareness**:
- Track where money is spent
- Set category-wise budgets per month
- Compare patterns over time
- Generate insight messages (spending up/down, top category, highest expense)

This is a practical consumer fintech MVP for personal money management.

## 3) Architecture (High Level)
- **Frontend:** React + Vite + Tailwind CSS + Recharts
- **Backend:** Spring Boot REST API
- **Security:** Spring Security + JWT (stateless auth)
- **Database:** JPA entities and repositories; local setup currently defaults to **H2 file DB**
- **Containerization:** Dockerfiles + `docker-compose.yml` (PostgreSQL service included)

Flow:
1. User logs in/registers from React app.
2. Backend returns JWT token.
3. Frontend stores token in localStorage and sends `Authorization: Bearer <token>`.
4. Backend JWT filter authenticates request.
5. Controllers call services, services call repositories, repositories query DB.
6. Frontend renders dashboards, charts, budgets, and insight cards.

## 4) Core Features Implemented
- User registration/login with encrypted passwords
- Protected routes and session validation
- Transaction CRUD (create/list/get/update/delete)
- Category-wise transaction filtering
- Budget creation and month-based retrieval
- Dashboard summary:
  - monthly spend
  - monthly budget
  - remaining budget
  - top category
  - predicted month-end spend
  - recent daily trend
  - recent transactions
- Insight generation for current month trends
- Receipt upload endpoint with lightweight OCR-style parsing (MVP parser)

## 5) Important Technical Terms Used Here
- REST API
- JWT authentication
- Stateless session
- BCrypt password hashing
- Spring Security filter chain
- Controller-Service-Repository pattern
- JPA/Hibernate ORM
- DTOs (Data Transfer Objects)
- Pagination (`Page`, `Pageable`)
- Aggregation queries (`SUM`, grouping by category/date)
- Validation (`@Valid`, bean validation)
- Global exception handling (`@RestControllerAdvice`)
- CORS config
- Migration SQL (Flyway script present)
- Docker Compose multi-service setup

## 6) Resume-Focused Tech Stack
- **Languages:** Java, JavaScript, SQL
- **Backend:** Spring Boot, Spring Web, Spring Data JPA, Spring Security, Validation
- **Auth/Security:** JWT (jjwt), BCrypt
- **Database:** H2 (active local default), PostgreSQL (compose setup/migration script intent)
- **Frontend:** React 18, React Router, Tailwind CSS, Recharts, Vite
- **DevOps/Tooling:** Maven, npm, Docker, Docker Compose

## 7) Realistic Resume Bullet Points You Can Use
- Built a full-stack personal finance application using Spring Boot and React with secure JWT-based authentication.
- Designed and implemented transaction and budget management modules with user-level data isolation and validation.
- Developed analytical dashboard endpoints for monthly spend summaries, top categories, trend computation, and projected month-end expenses.
- Implemented insight generation logic by comparing current and previous month spending patterns and category spikes.
- Created responsive frontend dashboards and visualizations using Tailwind CSS and Recharts.
- Added robust API error handling and auth invalidation flow for improved UX and session safety.

## 8) Important Observations (Good to Mention in Interview)
- `REQUIREMENTS.md` says PostgreSQL setup, but current `application.yml` defaults to H2 file DB.
- Flyway migration file exists, but `spring.flyway.enabled` is currently `false`.
- OCR is currently an MVP text parser (regex extraction), not full image OCR engine yet.

## 9) Suggested Project Title for Resume
- **PennyWise AI - Intelligent Personal Finance Tracker**

## 10) Elevator Description (30 Seconds)
PennyWise AI is a secure full-stack finance tracker where users can log transactions, define monthly budgets, and get analytical insights about spending behavior. I built JWT-based auth, backend aggregation APIs, and React dashboards with chart visualizations, making the app useful for both day-to-day expense management and higher-level spending analysis.
