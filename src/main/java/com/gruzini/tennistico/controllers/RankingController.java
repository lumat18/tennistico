package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.dto.PlayerDto;
import com.gruzini.tennistico.services.dto_related.PlayerDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ranking/page")
@RequiredArgsConstructor
public class RankingController {

  //set to 5 for testing purposes
  private static final int NUMBER_OF_PLAYERS_ON_PAGE = 5;

  private final PlayerDtoService playerDtoService;

  @GetMapping(value = "/{page}")
  public String listPlayersPageByPage(@PathVariable("page") int page,
                                      final Model model){
    PageRequest pageable = PageRequest.of(page - 1, NUMBER_OF_PLAYERS_ON_PAGE);
    final Page<PlayerDto> playerDtosPage = playerDtoService.getPlayerDtosPage(pageable);
    int numberOfPages = playerDtosPage.getTotalPages();
    model.addAttribute("numberOfPages", numberOfPages);
    model.addAttribute("currentPage", page);
    model.addAttribute("playerList", playerDtosPage.getContent());
    return "ranking";
  }
}
