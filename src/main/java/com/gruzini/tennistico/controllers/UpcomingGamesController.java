package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.mappers.GameInfoMapper;
import com.gruzini.tennistico.models.dto.GameInfoDto;
import com.gruzini.tennistico.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dashboard/upcoming")
public class UpcomingGamesController {
    private final GameService gameService;
    private final GameInfoMapper gameInfoMapper;

    public UpcomingGamesController(GameService gameService, GameInfoMapper gameInfoMapper) {
        this.gameService = gameService;
        this.gameInfoMapper = gameInfoMapper;
    }

    @GetMapping
    public String showUpcomingGamesPage(final Model model) {
        final List<GameInfoDto> allUpcomingGames = gameService.getAllUpcoming().stream()
                .map(gameInfoMapper::toGameInfoDto)
                .collect(Collectors.toList());
        model.addAttribute("upcomingGames", allUpcomingGames);
        return "upcoming";
    }
}
