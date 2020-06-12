package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.dto.HostedGameDto;
import com.gruzini.tennistico.services.HostedGameService;
import com.gruzini.tennistico.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dashboard/hosted")
public class HostedGamesController {
    private final HostedGameService gameService;
    private final UserService userService;

    public HostedGamesController(HostedGameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @GetMapping
    public String showUpcomingGamesPage(final Model model, final Principal principal) {
        final Player player = userService.getByEmail(principal.getName()).getPlayer();
        final List<HostedGameDto> allHostedGames = gameService.getAll(player);
        model.addAttribute("hostedGames", allHostedGames);
        return "hosted";
    }
}
