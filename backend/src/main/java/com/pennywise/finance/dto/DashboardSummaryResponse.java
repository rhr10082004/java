package com.pennywise.finance.dto;

import java.math.BigDecimal;
import java.util.List;

public record DashboardSummaryResponse(
        BigDecimal monthlyTotal,
        BigDecimal monthlyBudget,
        BigDecimal remainingBudget,
        String topCategory,
        BigDecimal predictedMonthlySpend,
        List<CategoryTotal> categoryTotals,
        List<DailySpend> recentDailyTrend,
        List<TransactionResponse> recentTransactions
) {
    public record CategoryTotal(String category, BigDecimal total) {}
    public record DailySpend(String date, BigDecimal total) {}
}
