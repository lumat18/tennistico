package com.gruzini.tennistico.controllers.rest;

import com.gruzini.tennistico.models.dto.PlayerDto;
import com.gruzini.tennistico.services.dto_related.PlayerDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlayerModalController {
  private final PlayerDtoService playerDtoService;

  @GetMapping(path = "/getPlayerDto")
  public PlayerDto fetchPlayerDtoById(@RequestParam Long id) {
    return playerDtoService.getPlayerDtoById(id);
  }
}
