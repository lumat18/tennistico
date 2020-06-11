package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard/upcoming")
public class UpcomingGamesController {
    private final GameService gameService;

    public UpcomingGamesController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String showUpcomingGamesPage(final Model model) {
        final List<Game> allUpcomingGames = gameService.getAllUpcoming();
        model.addAttribute("upcomingGames", allUpcomingGames);
        return "upcoming";
    }
}
