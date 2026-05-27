package com.pennywise.finance.service;

import com.pennywise.finance.dto.InsightResponse;
import com.pennywise.finance.entity.Insight;
import com.pennywise.finance.entity.Transaction;
import com.pennywise.finance.entity.User;
import com.pennywise.finance.repository.InsightRepository;
import com.pennywise.finance.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class InsightService {
    private final InsightRepository insightRepository;
    private final TransactionRepository transactionRepository;

    public InsightService(InsightRepository insightRepository, TransactionRepository transactionRepository) {
        this.insightRepository = insightRepository;
        this.transactionRepository = transactionRepository;
    }

    public InsightResponse create(User user, String type, String message) {
        Insight insight = new Insight();
        insight.setUser(user);
        insight.setType(type);
        insight.setMessage(message);
        Insight saved = insightRepository.save(insight);
        return toResponse(saved);
    }

    public Page<InsightResponse> getByUser(User user, Pageable pageable) {
        return insightRepository.findByUserOrderByCreatedAtDesc(user, pageable).map(this::toResponse);
    }

    public InsightResponse getById(Long id, User user) {
        Insight insight = insightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Insight not found"));
        if (!insight.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
        return toResponse(insight);
    }

    public void delete(Long id, User user) {
        Insight insight = insightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Insight not found"));
        if (!insight.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
        insightRepository.delete(insight);
    }

    public List<InsightResponse> generateForCurrentMonth(User user) {
        YearMonth now = YearMonth.now();
        YearMonth prev = now.minusMonths(1);
        int cy = now.getYear();
        int cm = now.getMonthValue();
        int py = prev.getYear();
        int pm = prev.getMonthValue();
        LocalDate monthStart = now.atDay(1);
        LocalDate monthEnd = now.atEndOfMonth();
        LocalDateTime dedupeAfter = monthStart.atStartOfDay();

        List<InsightResponse> generated = new ArrayList<>();
        BigDecimal currentTotal = nz(transactionRepository.getTotalByUserAndMonth(user, cy, cm));
        BigDecimal previousTotal = nz(transactionRepository.getTotalByUserAndMonth(user, py, pm));

        if (previousTotal.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal diff = currentTotal.subtract(previousTotal);
            if (diff.compareTo(BigDecimal.ZERO) > 0) {
                maybeAdd(generated, user, "MONTHLY_UP",
                        "Total spending is up by Rs " + diff.toPlainString() + " compared to last month.",
                        dedupeAfter);
            } else if (diff.compareTo(BigDecimal.ZERO) < 0) {
                maybeAdd(generated, user, "MONTHLY_DOWN",
                        "Total spending is down by Rs " + diff.abs().toPlainString() + " compared to last month.",
                        dedupeAfter);
            }
        }

        List<Object[]> currentCategoryTotals = transactionRepository.getCategoryTotalsByMonth(user, cy, cm);
        for (Object[] row : currentCategoryTotals) {
            String category = (String) row[0];
            BigDecimal curr = nz((BigDecimal) row[1]);
            BigDecimal old = nz(transactionRepository.getTotalByCategoryAndMonth(user, category, py, pm));
            if (curr.compareTo(old) > 0 && old.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal diff = curr.subtract(old);
                maybeAdd(generated, user, "CATEGORY_SPIKE",
                        category + " spending increased by Rs " + diff.toPlainString() + " compared to last month.",
                        dedupeAfter);
            }
        }

        if (!currentCategoryTotals.isEmpty()) {
            Object[] top = transactionRepository.getCategoryTotalsSorted(user, cy, cm).get(0);
            maybeAdd(generated, user, "TOP_CATEGORY",
                    "Your top spending category this month is " + top[0] + ".",
                    dedupeAfter);
        }

        List<Transaction> highTx = transactionRepository.findByUserAndTransactionDateBetweenOrderByAmountDesc(
                user, monthStart, monthEnd
        );
        if (!highTx.isEmpty()) {
            Transaction maxTx = highTx.get(0);
            maybeAdd(generated, user, "HIGHEST_TX",
                    "Highest single expense this month: Rs " + maxTx.getAmount().toPlainString() +
                            " on " + maxTx.getCategory() + ".",
                    dedupeAfter);
        }

        return generated;
    }

    private void maybeAdd(List<InsightResponse> generated, User user, String type, String message, LocalDateTime dedupeAfter) {
        if (!insightRepository.existsByUserAndTypeAndMessageAndCreatedAtAfter(user, type, message, dedupeAfter)) {
            generated.add(create(user, type, message));
        }
    }

    private InsightResponse toResponse(Insight insight) {
        return new InsightResponse(insight.getId(), insight.getType(), insight.getMessage());
    }

    private BigDecimal nz(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }
}
