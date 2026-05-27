# DESIGN DOCUMENT — PennyWise AI

## Finance Intelligence Platform

### Version 1.0

---

# 1. Design Vision

## Core Philosophy

The UI should feel:

- modern
- intelligent
- minimal
- trustworthy
- distraction-free

This is a finance product.

Not:

- a gaming dashboard
- a crypto scam website
- a neon cyberpunk experiment

Most student projects fail visually because they:

- overuse gradients
- add random animations
- overcrowd dashboards
- ignore spacing hierarchy

Your design advantage should be:

## clarity + simplicity + professionalism

---

# 2. Design Goals

## Primary Goals

- easy expense tracking
- fast understanding of spending
- clean data visualization
- mobile responsiveness
- minimal friction

---

## User Feeling

When users open the app they should feel:

- organized
- in control
- financially aware

NOT overwhelmed.

---

# 3. Design Language

# Style Direction

Minimal fintech dashboard UI.

Inspired by:

- modern banking apps
- Notion simplicity
- Stripe dashboard cleanliness

---

# 4. Color System

## Primary Colors

### Background

```css
#0F172A
```

Dark navy background.

---

### Card Background

```css
#1E293B
```

---

### Primary Accent

```css
#3B82F6
```

Used for:

- buttons
- highlights
- active states

---

### Success / Positive

```css
#22C55E
```

Used for:

- budget remaining
- positive insights

---

### Warning

```css
#F59E0B
```

Used for:

- overspending warnings

---

### Danger

```css
#EF4444
```

Used for:

- critical budget exceeded
- delete actions

---

### Text Colors

Primary:

```css
#F8FAFC
```

Secondary:

```css
#94A3B8
```

---

# 5. Typography

## Font Recommendation

Use:

## Inter

Why:

- modern
- readable
- professional
- widely used in fintech products

---

# Typography Scale

## Headings

```css
font-weight: 700;
```

---

## Body Text

```css
font-weight: 400;
```

---

## Analytics Numbers

```css
font-weight: 800;
```

Large bold numbers for financial visibility.

---

# 6. Spacing System

## Rule

Your UI should breathe.

Most beginners destroy UI by:

- compressing components
- inconsistent margins
- random padding

---

## Standard Spacing

```css
4px
8px
16px
24px
32px
48px
```

Use consistently.

---

# 7. Layout Structure

# Main App Layout

```text id="6e8uxb"
--------------------------------
Sidebar |        Navbar
        |
        |    Main Dashboard
        |
        |
--------------------------------
```

---

# Sidebar

## Contains

- Dashboard
- Transactions
- Budgets
- Analytics
- Logout

---

## Sidebar Style

- fixed width
- dark background
- icon + text navigation

---

# Navbar

## Contains

- search bar
- profile section
- notification icon

Keep minimal.

---

# 8. Page Designs

# Landing Page

## Goal

Explain product quickly.

Most users decide within:

## 5 seconds

---

## Sections

### Hero Section

Contains:

- product headline
- short description
- CTA button
- dashboard preview image

---

### Features Section

3–4 cards only:

- AI Insights
- Budget Tracking
- OCR Scanner
- Expense Analytics

---

### Footer

Simple.
No giant corporate footer.

---

# Login/Register Page

## Design Rules

- centered form
- minimal inputs
- clean validation
- no clutter

---

## Layout

```text id="d5cqwv"
-------------------------
        Logo

    Welcome Back

    Email Input
    Password Input

      Login Button

   Create Account
-------------------------
```

---

# Dashboard Design

# Core Principle

Dashboard must prioritize:

## understanding money instantly

---

# Dashboard Sections

## Section 1 — Overview Cards

### Cards

- Total Expenses
- Remaining Budget
- Top Category
- Monthly Prediction

---

## Card Design

- rounded corners
- soft shadows
- icon on left
- analytics value large

---

# Section 2 — Charts

## Left

Pie Chart:

- category spending

---

## Right

Line Chart:

- weekly spending trend

---

# Section 3 — Recent Transactions

## Table Columns

- category
- amount
- date
- payment method

---

## Important

Do NOT overload table.

Keep clean.

---

# 9. Transaction Page

# Layout

```text id="u9p6g0"
--------------------------------
 Add Transaction Button

 Filters/Search

 Transactions Table
--------------------------------
```

---

# Add Transaction Modal

## Fields

- amount
- category
- payment method
- date
- note

---

## UI Rules

- clean spacing
- simple dropdowns
- proper validation

---

# 10. Budget Page

# Layout

Each category shown as:

- budget limit
- current spending
- remaining amount
- progress bar

---

# Progress Bar Colors

## Safe

Green

---

## Warning

Yellow

---

## Exceeded

Red

---

# 11. Analytics Page

# Purpose

Show spending behavior patterns.

---

# Components

## Charts

- monthly trend
- category comparison
- budget utilization

---

## AI Insight Cards

Example:

```text id="71u0qy"
Food spending increased 18% this month.
```

---

# Design Rule

Insights must:

- stand out visually
- be short
- easy to scan

---

# 12. OCR Upload UI

# Workflow

```text id="l2sp2q"
Upload Receipt
      ↓
Image Preview
      ↓
Processing Loader
      ↓
Extracted Data
      ↓
Confirm & Save
```

---

# Important

Users must always:

- edit extracted values manually

OCR is never perfect.

---

# 13. Component Design Rules

# Buttons

## Primary Button

- solid blue
- rounded
- medium shadow

---

## Secondary Button

- outlined
- muted colors

---

# Inputs

## Style

- dark input background
- subtle borders
- focus glow

---

# Cards

## Rules

- equal padding
- consistent radius
- subtle shadows

---

# Tables

## Rules

- zebra striping optional
- readable spacing
- responsive scrolling

---

# 14. Responsive Design

# Mobile Rules

Most students completely ignore mobile.

Huge mistake.

---

# Mobile Layout

## Sidebar

Convert into:

- hamburger menu

---

## Charts

Stack vertically.

---

## Cards

Single-column layout.

---

# Responsive Breakpoints

```css
sm → mobile
md → tablet
lg → desktop
```

---

# 15. Animation Guidelines

# Important Rule

Animations should:

- support UX
- not distract

---

# Use Animations For

- hover effects
- loading states
- modal opening
- smooth transitions

---

# Avoid

- random floating elements
- excessive motion
- flashy animations

---

# 16. Empty States

Most students forget this.

Professional apps don’t.

---

# Example

No transactions:

```text id="4m3hwf"
"No expenses added yet."
```

With CTA:

```text id="4r3svw"
"Add Your First Expense"
```

---

# 17. Loading States

# Required

Use:

- skeleton loaders
- button loading states
- chart loading placeholders

---

# Avoid

Plain:

```text id="3m6gf5"
Loading...
```

Looks amateur.

---

# 18. Error Handling UI

# Good Error Message

```text id="wlxg6w"
"Failed to save transaction. Please try again."
```

---

# Bad Error Message

```text id="ql0jj5"
"Something went wrong"
```

Too vague.

---

# 19. Accessibility Basics

## Must Include

- proper contrast
- readable font sizes
- keyboard navigation
- form labels

---

# 20. Final Design Priorities

# Priority Order

```text id="x5ynvh"
Usability
   ↓
Clarity
   ↓
Consistency
   ↓
Responsiveness
   ↓
Visual Beauty
```

Most beginners reverse this order.

That’s why their projects look impressive for 5 seconds and terrible after actual usage.

---

# 21. Final UI Success Criteria

Your UI succeeds if:

- users understand spending instantly
- navigation feels obvious
- data feels readable
- app feels professional
- dashboard feels useful

Not if:

- it has fancy animations
- it looks “cool”
- it uses 50 colors
- it copies random Dribbble shots blindly
