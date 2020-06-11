package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.mappers.ArchivedGameMapper;
import com.gruzini.tennistico.models.forms.ArchivedGameDTO;
import com.gruzini.tennistico.services.GameService;
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
   private final GameService gameService;
   private final UserService userService;
   private final ArchivedGameMapper mapper;

   public GamesHistoryController(final GameService gameService, final UserService userService, final ArchivedGameMapper mapper) {
      this.gameService = gameService;
      this.userService = userService;
      this.mapper = mapper;
   }

   @GetMapping
   public String showGamesHistoryPage(final Model model,
                                      final Principal principal){
      final User user = userService.getByEmail(principal.getName());
      final List<Game> allArchivedGamesByPlayer =
              gameService.findAllArchivedGamesByPlayer(user.getPlayer(), GameStatus.ARCHIVED);
      final List<ArchivedGameDTO> archivedGameDTOList = allArchivedGamesByPlayer.stream()
              .map(game -> mapper.toArchivedGameDTO(game, user.getPlayer()))
              .collect(Collectors.toList());
      model.addAttribute("archivedGames", archivedGameDTOList);
      return "dashboard/games-history";
   }
}
