package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.dto.PlayerDto;
import com.gruzini.tennistico.services.dto_related.PlayerDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class RankingController {

  //set to 5 for testing purposes
  private static final int NUMBER_OF_PLAYERS_ON_PAGE = 5;

  private final PlayerDtoService playerDtoService;

  @RequestMapping(value = "/ranking/page/{page}")
  public String listPlayersPageByPage(@PathVariable("page") int page,
                                      final Model model){
    PageRequest pageable = PageRequest.of(page - 1, NUMBER_OF_PLAYERS_ON_PAGE);
    final Page<PlayerDto> playerDtosPage = playerDtoService.getPlayerDtosPage(pageable);
    int totalPages = playerDtosPage.getTotalPages();
    if(totalPages > 0){
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
      model.addAttribute("pageNumbers", pageNumbers);
    }
    model.addAttribute("activePlayerList", true);
    model.addAttribute("playerList", playerDtosPage.getContent());
    return "ranking";
  }
}
