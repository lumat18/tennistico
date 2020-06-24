package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.dto.NotificationDto;
import com.gruzini.tennistico.services.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final NotificationService notificationService;

    public DashboardController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public String showDashboard(final Model model, final Principal principal){
        final List<NotificationDto> notifications = notificationService.getByUser(principal.getName());
        model.addAttribute("actionMessage", "Welcome to tennistico!");
        model.addAttribute("notifications", notifications);
        return "dashboard";
    }
}
