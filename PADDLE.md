# PADDLE - Project Breakdown (PennyWise AI)

## P - Problem
People track expenses manually but rarely get clear spending intelligence. They need budgeting + trend visibility in one simple app.

## A - Approach
Built a full-stack architecture:
- React frontend for user interaction
- Spring Boot backend for business logic and APIs
- JWT security for stateless authenticated access
- Database-backed transaction, budget, and insight modules

## D - Design
- Layered backend: Controller -> Service -> Repository
- DTO-driven API contracts
- Entity relationships centered around `User`
- Protected routes + token-based API access in frontend
- Dashboard and analytics pages to convert raw data into insights

## D - Data Model
- `users`: user identity and credentials
- `transactions`: amount/category/method/date/note linked to user
- `budgets`: monthly category budget limits
- `insights`: generated messages for behavioral patterns

## L - Logic
- Category validation via allow-list
- Monthly aggregations (`SUM`, grouped totals)
- Predicted monthly spend using current spend/day * total month days
- Insight generation for:
  - monthly up/down vs previous month
  - category spike detection
  - top category
  - highest transaction
- Duplicate-insight prevention for current month window

## E - Execution
- Backend: Maven build, Spring Boot runtime
- Frontend: Vite dev server
- Optional Docker Compose with db/backend/frontend services
- Current local default DB is H2 file mode; compose setup uses PostgreSQL
