package com.pennywise.finance.service;

import com.pennywise.finance.dto.BudgetRequest;
import com.pennywise.finance.dto.BudgetResponse;
import com.pennywise.finance.entity.Budget;
import com.pennywise.finance.entity.User;
import com.pennywise.finance.repository.BudgetRepository;
import com.pennywise.finance.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final TransactionRepository transactionRepository;

    public BudgetService(BudgetRepository budgetRepository, TransactionRepository transactionRepository) {
        this.budgetRepository = budgetRepository;
        this.transactionRepository = transactionRepository;
    }

    public BudgetResponse create(User user, BudgetRequest request) {
        budgetRepository.findByUserAndCategoryAndBudgetMonth(user, request.category(), request.budgetMonth())
                .ifPresent(b -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Budget already exists for this category and month");
                });

        Budget budget = new Budget();
        budget.setUser(user);
        budget.setCategory(request.category());
        budget.setBudgetMonth(request.budgetMonth());
        budget.setLimitAmount(request.limitAmount());
        budget.setCreatedAt(LocalDateTime.now());

        Budget saved = budgetRepository.save(budget);
        return toResponse(saved, user);
    }

    public List<BudgetResponse> getByMonth(User user, String budgetMonth) {
        return budgetRepository.findByUserAndBudgetMonth(user, budgetMonth)
                .stream()
                .map(b -> toResponse(b, user))
                .toList();
    }

    public BudgetResponse getById(Long id, User user) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget not found"));

        if (!budget.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        return toResponse(budget, user);
    }

    public BudgetResponse update(Long id, User user, BudgetRequest request) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget not found"));

        if (!budget.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        budget.setLimitAmount(request.limitAmount());
        Budget updated = budgetRepository.save(budget);
        return toResponse(updated, user);
    }

    public void delete(Long id, User user) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget not found"));

        if (!budget.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        budgetRepository.delete(budget);
    }

    private BudgetResponse toResponse(Budget budget, User user) {
        String[] parts = budget.getBudgetMonth().split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        BigDecimal spent = transactionRepository.getTotalByCategoryAndMonth(user, budget.getCategory(), year, month);
        if (spent == null) {
            spent = BigDecimal.ZERO;
        }

        return new BudgetResponse(
                budget.getId(),
                budget.getCategory(),
                budget.getBudgetMonth(),
                budget.getLimitAmount(),
                spent
        );
    }
}
