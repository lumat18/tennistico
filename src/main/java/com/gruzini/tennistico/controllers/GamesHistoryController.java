package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.mappers.ArchivedGameMapper;
import com.gruzini.tennistico.models.forms.ArchivedGameDTO;
import com.gruzini.tennistico.services.ArchivedGameService;
import com.gruzini.tennistico.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dashboard/games-history")
public class GamesHistoryController {
   private final ArchivedGameService archivedGameService;
   private final UserService userService;

   public GamesHistoryController(final ArchivedGameService archivedGameService,
                                 final UserService userService) {
      this.archivedGameService = archivedGameService;
      this.userService = userService;
   }

   @GetMapping
   public String showGamesHistoryPage(final Model model,
                                      final Principal principal){
      final User user = userService.getByEmail(principal.getName());
      model.addAttribute("archivedGames", archivedGameService.getUserGames(user));
      return "dashboard/games-history";
   }
}
