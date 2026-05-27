package com.pennywise.finance.dto;

import java.math.BigDecimal;

public record BudgetResponse(
    Long id,
    String category,
    String budgetMonth,
    BigDecimal limitAmount,
    BigDecimal spent
) {}
