package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.dto.NotificationDto;
import com.gruzini.tennistico.services.NotificationService;
import com.gruzini.tennistico.services.entity_related.UserService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final NotificationService notificationService;
    private final UserService userService;

    public DashboardController(final NotificationService notificationService, final UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @GetMapping
    public String showDashboard(final Model model, final Principal principal, final HttpSession session){
        final List<NotificationDto> notifications = notificationService.getByUser(principal.getName());
        model.addAttribute("notifications", notifications);
        session.setAttribute("loggedUser", userService.getByEmail(principal.getName()));
        return "dashboard";
    }
}
