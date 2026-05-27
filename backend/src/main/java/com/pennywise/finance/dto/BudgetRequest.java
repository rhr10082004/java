package com.pennywise.finance.dto;

import java.math.BigDecimal;

public record BudgetRequest(
    String category,
    String budgetMonth,
    BigDecimal limitAmount
) {}
