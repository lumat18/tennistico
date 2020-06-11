package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.mappers.UpcomingGameMapper;
import com.gruzini.tennistico.models.dto.UpcomingGameDto;
import com.gruzini.tennistico.services.UpcomingGameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dashboard/upcoming")
public class UpcomingGamesController {
    private final UpcomingGameService gameService;

    public UpcomingGamesController(UpcomingGameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String showUpcomingGamesPage(final Model model) {
        final List<UpcomingGameDto> allUpcomingGames = gameService.getAll();
        model.addAttribute("upcomingGames", allUpcomingGames);
        return "upcoming";
    }
}
