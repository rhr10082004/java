PRD — Finance Intelligence Platform
Project Name: PennyWise AI

1. Product Overview
   Problem

Most expense trackers are useless because they only store transactions.

They do not:

explain spending behavior
predict future expenses
identify financial leaks
help users improve habits

Users end up abandoning them after a few days.

Solution

Build a smart finance intelligence platform that:

tracks expenses automatically
analyzes spending behavior
gives actionable AI insights
predicts monthly spending
helps users improve financial discipline

This is NOT another CRUD expense tracker.

It is a lightweight financial behavior analysis system.

2. Product Goal

Build an MVP that demonstrates:

strong Java backend skills
analytics processing
AI integration
clean UI/UX
scalable architecture
real-world utility

The platform should feel:

minimal
fast
intelligent
practical 3. Core Features (ONLY Important Features)
Feature 1 — Smart Expense Dashboard
Priority: HIGH
Description

Central dashboard showing:

total monthly spending
category-wise spending
recent transactions
budget usage
spending trend graph
Why Important

This is the heart of the product.

Without a strong dashboard:
the app feels useless.

Components
Monthly expense card
Budget remaining card
Pie chart by category
Weekly trend graph
Top spending category
Recent transaction table
Feature 2 — Transaction Management
Priority: HIGH
Description

Users can:

add expenses manually
edit/delete transactions
categorize transactions
Transaction Fields
amount
category
payment method
date
note
Categories

Keep fixed initially:

Food
Shopping
Travel
Bills
Entertainment
Health
Education
Other

Do NOT overengineer categories.

Feature 3 — OCR Receipt Scanner
Priority: HIGH
Description

User uploads receipt image.

System extracts:

amount
merchant
date

Then auto-fills transaction form.

Why This Matters

This instantly makes the project:

practical
modern
technically impressive

Most student finance apps don’t have this.

Suggested Implementation

Frontend:

image upload

Backend:

OCR API / Tesseract

Flow:
Upload → OCR → Parse → Autofill → Save

Feature 4 — AI Spending Insights
Priority: VERY HIGH

This is your killer feature.

Description

AI analyzes spending patterns and generates insights like:

Examples:

“Food spending increased 28% this week.”
“You spent more on subscriptions than education.”
“Weekend spending is significantly higher.”
“Shopping expenses crossed your average threshold.”
Important Rule

Insights MUST be:

short
practical
data-backed

Do NOT generate motivational garbage.

Bad:

“You should save money wisely.”

Good:

“Your food expenses increased by ₹2400 compared to last month.”

How to Build

Backend analytics engine:

compare monthly totals
detect category spikes
identify patterns

Optional:
Use AI API for natural language formatting.

Feature 5 — Budget Goal Tracking
Priority: HIGH
Description

Users set monthly budgets:

overall
category-wise

System tracks:

remaining amount
overspending alerts
Example

Food Budget:
₹5000

Current:
₹4200

Remaining:
₹800

Progress bar visualization.

Feature 6 — Monthly Expense Prediction
Priority: MEDIUM
Description

Predict end-of-month spending based on current trends.

Example:

“At your current rate, you may spend ₹18,500 this month.”

Why Important

Shows:

analytics capability
predictive thinking
Simple Logic

No ML needed initially.

Formula:
(Current Spend / Current Day) × Total Days

Simple.
Effective.
Enough for MVP.

4. Features to AVOID

Do NOT add:

crypto tracking
stock market
social feed
chat system
UPI integration
bank account integration
investment advice
multiple currencies
AI chatbot

These are distractions.

Your goal:
QUALITY OVER QUANTITY.

5. User Flow
   Flow 1 — Add Expense

Login → Dashboard → Add Transaction → Save → Dashboard Updates

Flow 2 — OCR Receipt

Upload Receipt → OCR Extraction → Autofill Form → Confirm → Save

Flow 3 — Budget Tracking

Set Budget → Add Expenses → Progress Updates → Alert Triggered

Flow 4 — AI Insights

Transactions Stored → Analytics Engine Runs → Insights Generated → Dashboard Display

6. Tech Stack
   Frontend
   React
   Tailwind CSS
   Recharts
   Backend
   Java Spring Boot
   Required Modules
   Spring Security
   JWT Authentication
   Spring Data JPA
   REST APIs
   Database
   PostgreSQL
   OCR

Options:

Tesseract OCR
OR
Google Vision API
Deployment

Frontend:

Vercel

Backend:

Render / Railway

Database:

Neon PostgreSQL 7. Backend Architecture
Main Entities
User
id
name
email
password
Transaction
id
amount
category
date
paymentMethod
note
userId
Budget
id
category
limit
spent
userId
Insight
id
message
type
createdAt
userId 8. API Structure
Auth APIs
POST /register
POST /login
Transaction APIs
GET /transactions
POST /transactions
PUT /transactions/{id}
DELETE /transactions/{id}
Budget APIs
GET /budgets
POST /budgets
Insight APIs
GET /insights
OCR API
POST /receipt/upload 9. UI Pages
Pages Needed
Public
Landing Page
Login
Register
Private
Dashboard
Transactions
Budgets
Analytics 10. UI Design Direction
Style

Minimal modern fintech UI.

Think:

dark mode
glassmorphism cards
smooth charts
simple typography
Colors

Avoid:

random gradients everywhere
neon colors
gaming aesthetics

Use:

clean whites/blacks
muted green
soft blue

Make it feel like a real product.

11. MVP Scope
    Build FIRST
    Authentication
    Dashboard
    Transactions
    Budget Tracking
    AI Insights

ONLY.

Build SECOND
OCR Scanner
Predictions
Advanced Analytics 12. Resume Value

This project demonstrates:

Java backend development
REST API design
authentication
analytics systems
OCR integration
data visualization
AI-assisted insights
fullstack deployment

This is significantly stronger than generic CRUD projects.

13. Success Criteria

Project is successful if:

users can track spending easily
insights feel intelligent
UI feels production-ready
analytics are useful
deployment works smoothly

Not if:

it has 50 features
it uses 20 technologies
it looks complicated 14. Biggest Risk

Your biggest risk is feature addiction.

You will feel tempted to:

add AI everywhere
add bank integration
add complex ML
add notifications
rebuild the frontend 20 times

That will kill the project.

Your goal:

Ship a polished MVP first.
