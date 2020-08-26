package com.gruzini.tennistico.controllers.rest;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.mappers.MatchInfoParser;
import com.gruzini.tennistico.models.dto.PlayerDto;
import com.gruzini.tennistico.services.dto_related.PlayerDtoService;
import com.gruzini.tennistico.services.entity_related.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class PlayerModalController {
  private final PlayerDtoService playerDtoService;
  private final UserService userService;
  private final MatchInfoParser matchInfoParser;

  @GetMapping(path = "/getPlayerDto")
  public PlayerDto fetchPlayerDtoById(@RequestParam Long id) {
    return playerDtoService.getPlayerDtoById(id);
  }

  @GetMapping(path = "/getPlayerDtoByMatchId")
  public PlayerDto fetchPlayerDtoByMatchId(@RequestParam Long id, final Principal principal){
    final Player player = userService.getByEmail(principal.getName()).getPlayer();
    return playerDtoService.getPlayerDtoById(matchInfoParser.getOpponentId(id, player));
  }
}
