package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.dto.HostedGameDto;
import com.gruzini.tennistico.services.HostedGameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard/hosted")
public class HostedGamesController {
    private final HostedGameService hostedGameService;

    public HostedGamesController(HostedGameService hostedGameService) {
        this.hostedGameService = hostedGameService;
    }

    @GetMapping
    public String showUpcomingGamesPage(final Model model) {
        final List<HostedGameDto> allHostedGames = hostedGameService.getAll();
        model.addAttribute("hostedGames", allHostedGames);
        return "hosted";
    }
}
