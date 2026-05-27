package com.pennywise.finance.controller;

import com.pennywise.finance.dto.InsightResponse;
import com.pennywise.finance.entity.User;
import com.pennywise.finance.service.AuthService;
import com.pennywise.finance.service.InsightService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/insights")
public class InsightController {
    private final InsightService insightService;
    private final AuthService authService;

    public InsightController(InsightService insightService, AuthService authService) {
        this.insightService = insightService;
        this.authService = authService;
    }

    @GetMapping
    public Page<InsightResponse> list(Pageable pageable, Principal principal) {
        User user = authService.getByEmail(principal.getName());
        return insightService.getByUser(user, pageable);
    }

    @GetMapping("/{id}")
    public InsightResponse getById(@PathVariable Long id, Principal principal) {
        User user = authService.getByEmail(principal.getName());
        return insightService.getById(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, Principal principal) {
        User user = authService.getByEmail(principal.getName());
        insightService.delete(id, user);
    }

    @PostMapping("/generate")
    public List<InsightResponse> generate(Principal principal) {
        User user = authService.getByEmail(principal.getName());
        return insightService.generateForCurrentMonth(user);
    }
}
