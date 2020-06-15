package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.services.ConfirmScoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/confirm-score")
public class ConfirmScoreController {

   private final ConfirmScoreService confirmScoreService;

   public ConfirmScoreController(final ConfirmScoreService confirmScoreService) {
      this.confirmScoreService = confirmScoreService;
   }

   @GetMapping("/{match_id}")
   public String confirmScoreSetByHost(@PathVariable(name = "match_id")final Long matchId,
                                       final Principal principal){
      confirmScoreService.confirmScore(matchId, principal.getName());
      return "dashboard";
   }
}
