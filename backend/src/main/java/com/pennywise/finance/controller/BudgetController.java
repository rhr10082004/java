package com.pennywise.finance.controller;

import com.pennywise.finance.dto.BudgetRequest;
import com.pennywise.finance.dto.BudgetResponse;
import com.pennywise.finance.entity.User;
import com.pennywise.finance.service.AuthService;
import com.pennywise.finance.service.BudgetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
    private final BudgetService budgetService;
    private final AuthService authService;

    public BudgetController(BudgetService budgetService, AuthService authService) {
        this.budgetService = budgetService;
        this.authService = authService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BudgetResponse create(@Valid @RequestBody BudgetRequest request, Principal principal) {
        User user = authService.getByEmail(principal.getName());
        return budgetService.create(user, request);
    }

    @GetMapping
    public List<BudgetResponse> getByMonth(@RequestParam String budgetMonth, Principal principal) {
        User user = authService.getByEmail(principal.getName());
        return budgetService.getByMonth(user, budgetMonth);
    }

    @GetMapping("/{id}")
    public BudgetResponse getById(@PathVariable Long id, Principal principal) {
        User user = authService.getByEmail(principal.getName());
        return budgetService.getById(id, user);
    }

    @PutMapping("/{id}")
    public BudgetResponse update(@PathVariable Long id, @Valid @RequestBody BudgetRequest request, Principal principal) {
        User user = authService.getByEmail(principal.getName());
        return budgetService.update(id, user, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, Principal principal) {
        User user = authService.getByEmail(principal.getName());
        budgetService.delete(id, user);
    }
}
