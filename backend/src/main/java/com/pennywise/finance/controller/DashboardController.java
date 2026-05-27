package com.pennywise.finance.controller;

import com.pennywise.finance.dto.DashboardSummaryResponse;
import com.pennywise.finance.entity.User;
import com.pennywise.finance.service.AuthService;
import com.pennywise.finance.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;
    private final AuthService authService;

    public DashboardController(DashboardService dashboardService, AuthService authService) {
        this.dashboardService = dashboardService;
        this.authService = authService;
    }

    @GetMapping("/summary")
    public DashboardSummaryResponse summary(Principal principal) {
        User user = authService.getByEmail(principal.getName());
        return dashboardService.getSummary(user);
    }
}
