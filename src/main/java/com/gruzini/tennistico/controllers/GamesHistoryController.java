package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/games-history")
public class GamesHistoryController {
   private final GameService gameService;

   public GamesHistoryController(final GameService gameService) {
      this.gameService = gameService;
   }

   @GetMapping
   public String showGamesHistoryPage(){
      return "dashboard/games-history";
   }
}
