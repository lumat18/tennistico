package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.services.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final NotificationService notificationService;

    public DashboardController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public String showDashboard(final Model model, final Principal principal){
        model.addAttribute("notifications", notificationService.getByUser(principal.getName()));
        return "dashboard";
    }
}
