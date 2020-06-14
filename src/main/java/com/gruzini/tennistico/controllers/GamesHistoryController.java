package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.services.GameDtoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/dashboard/games-history")
public class GamesHistoryController {
   private final GameDtoService gameDtoService;

   public GamesHistoryController(GameDtoService gameDtoService) {
      this.gameDtoService = gameDtoService;
   }

   @GetMapping
   public String showGamesHistoryPage(final Model model,
                                      final Principal principal) {
      model.addAttribute("archivedGames", gameDtoService.getArchiveGameDtoBy(principal.getName()));
      return "games-history";
   }
}
