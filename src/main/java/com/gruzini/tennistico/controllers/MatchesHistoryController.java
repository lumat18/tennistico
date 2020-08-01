package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.services.dto_related.MatchDtoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/dashboard/matches-history")
public class MatchesHistoryController {
   private final MatchDtoService matchDtoService;

   public MatchesHistoryController(MatchDtoService matchDtoService) {
      this.matchDtoService = matchDtoService;
   }

   @GetMapping
   public String showMatchesHistoryPage(final Model model,
                                        final Principal principal) {
      model.addAttribute("archivedMatches", matchDtoService.getArchiveMatchDtosBy(principal.getName()));
      return "matches-history";
   }
}
