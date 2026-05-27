package com.pennywise.finance.service;

import com.pennywise.finance.dto.DashboardSummaryResponse;
import com.pennywise.finance.dto.TransactionResponse;
import com.pennywise.finance.entity.User;
import com.pennywise.finance.repository.BudgetRepository;
import com.pennywise.finance.repository.TransactionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class DashboardService {
    private final TransactionRepository transactionRepository;
    private final BudgetRepository budgetRepository;
    private final TransactionService transactionService;

    public DashboardService(TransactionRepository transactionRepository, BudgetRepository budgetRepository, TransactionService transactionService) {
        this.transactionRepository = transactionRepository;
        this.budgetRepository = budgetRepository;
        this.transactionService = transactionService;
    }

    public DashboardSummaryResponse getSummary(User user) {
        YearMonth ym = YearMonth.now();
        int year = ym.getYear();
        int month = ym.getMonthValue();
        String budgetMonth = ym.toString();

        BigDecimal monthlyTotal = nz(transactionRepository.getTotalByUserAndMonth(user, year, month));
        BigDecimal monthlyBudget = nz(budgetRepository.getTotalLimitByMonth(user, budgetMonth));
        BigDecimal remaining = monthlyBudget.subtract(monthlyTotal);

        String topCategory = "N/A";
        List<Object[]> topRows = transactionRepository.getCategoryTotalsSorted(user, year, month);
        if (!topRows.isEmpty()) {
            topCategory = (String) topRows.get(0)[0];
        }

        int today = LocalDate.now().getDayOfMonth();
        int daysInMonth = ym.lengthOfMonth();
        BigDecimal predicted = BigDecimal.ZERO;
        if (today > 0) {
            predicted = monthlyTotal.divide(BigDecimal.valueOf(today), 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(daysInMonth));
        }

        List<DashboardSummaryResponse.CategoryTotal> categoryTotals = transactionRepository.getCategoryTotalsByMonth(user, year, month)
                .stream()
                .map(r -> new DashboardSummaryResponse.CategoryTotal((String) r[0], nz((BigDecimal) r[1])))
                .toList();

        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(6);
        List<DashboardSummaryResponse.DailySpend> trend = transactionRepository.getDailyTotalsBetween(user, start, end)
                .stream()
                .map(r -> new DashboardSummaryResponse.DailySpend(r[0].toString(), nz((BigDecimal) r[1])))
                .toList();

        List<TransactionResponse> recent = transactionService.getByUser(user, PageRequest.of(0, 5)).getContent();

        return new DashboardSummaryResponse(
                monthlyTotal, monthlyBudget, remaining, topCategory, predicted, categoryTotals, trend, recent
        );
    }

    private BigDecimal nz(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }
}
