# Phase 0 Foundation

## Scope Lock

MVP includes:

- Authentication
- Dashboard analytics
- Transaction management
- Budget tracking
- AI insights (rule-based)

Deferred:

- OCR receipt scanner
- Monthly prediction card

## Backend Package Architecture

```text
com.pennywise.finance
  controller
  service
  repository
  entity
  dto
  security
  config
  exception
```

## Frontend Architecture

```text
src
  components
  layouts
  pages
  routes
  services
  hooks
  utils
  context
```

## API Surface (Phase 0-1 planned)

- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/health`

## Data Model (initial)

- `users`
- `transactions`
- `budgets`
- `insights`
