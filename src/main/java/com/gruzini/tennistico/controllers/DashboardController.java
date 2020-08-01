package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.dto.NotificationDto;
import com.gruzini.tennistico.services.dto_related.MatchDtoService;
import com.gruzini.tennistico.services.dto_related.PlayerDtoService;
import com.gruzini.tennistico.services.notifications.NotificationService;
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
    private final PlayerDtoService playerDtoService;
    private final MatchDtoService matchDtoService;

    public DashboardController(final NotificationService notificationService, final PlayerDtoService playerDtoService, MatchDtoService matchDtoService) {
        this.notificationService = notificationService;
        this.playerDtoService = playerDtoService;
        this.matchDtoService = matchDtoService;
    }

    @GetMapping
    public String showDashboard(final Model model, final Principal principal, final HttpSession session) {
        final List<NotificationDto> notifications = notificationService.getByUser(principal.getName());
        model.addAttribute("actionMessage", "Welcome to tennistico!");
        model.addAttribute("notifications", notifications);
        model.addAttribute("lastMatch", matchDtoService.getLastArchivedMatchDtoBy(principal.getName()));
        session.setAttribute("loggedUser", playerDtoService.getPlayerDtoByEmail(principal.getName()));
        return "dashboard";
    }
}
