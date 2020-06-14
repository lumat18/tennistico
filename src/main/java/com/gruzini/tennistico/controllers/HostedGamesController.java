package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.dto.HostedGameDto;
import com.gruzini.tennistico.services.GameDtoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dashboard/hosted")
public class HostedGamesController {
    private final GameDtoService gameDtoService;

    public HostedGamesController(GameDtoService gameDtoService) {
        this.gameDtoService = gameDtoService;
    }

    @GetMapping
    public String showUpcomingGamesPage(final Model model, final Principal principal) {
        final List<HostedGameDto> allHostedGames = gameDtoService.getHostedGameDtoExceptHostedBy(principal.getName());
        model.addAttribute("hostedGames", allHostedGames);
        return "hosted";
    }
}
