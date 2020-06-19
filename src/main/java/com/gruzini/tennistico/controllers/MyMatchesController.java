package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.dto.matchDto.FutureMatchDto;
import com.gruzini.tennistico.services.dto_related.MatchDtoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dashboard/my-matches")
public class MyMatchesController {
   private final MatchDtoService matchDtoService;

   public MyMatchesController(final MatchDtoService matchDtoService) {
      this.matchDtoService = matchDtoService;
   }

   @GetMapping
   public String showAllFutureMatchesPlayerIsInvolvedIn(final Model model, final Principal principal){
      final List<FutureMatchDto> allFutureMatches = matchDtoService.getAllFutureMatchesPlayerIsInvolvedIn(principal.getName());
      model.addAttribute("myMatches", allFutureMatches);
      return "my-matches";
   }
}
