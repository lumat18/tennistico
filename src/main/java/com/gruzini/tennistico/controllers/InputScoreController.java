package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.events.InputScoreEvent;
import com.gruzini.tennistico.models.dto.ScoreDto;
import org.springframework.context.ApplicationEventPublisher;
import com.gruzini.tennistico.services.entities.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/input-score")
public class InputScoreController {

    private final ApplicationEventPublisher applicationEventPublisher;
   private final MatchService matchService;

   //    @PostMapping("/begin")
   @GetMapping("/{match_id}")
   public String beginSubmittingScore(/*@RequestParam(name = "matchId")*/ @PathVariable(name = "match_id") final Long matchId, final Model model) {
      model.addAttribute("match", matchService.getById(matchId));
      model.addAttribute("score", new ScoreDto());
      return "score";
   }

   @PostMapping("/process")
   public String processSubmittedScore(@ModelAttribute(name = "match_id") final Long matchId,
                                       @Valid @ModelAttribute(name = "score") final ScoreDto scoreDTO,
                                       final Errors errors,
                             final Model model,
                             final Principal principal) {
      if (errors.hasErrors()) {
         model.addAttribute("match", matchService.getById(matchId));
         model.addAttribute("score", new ScoreDto());
         model.addAttribute("msg", "Invalid score input");
         return "score";
      }
        applicationEventPublisher.publishEvent(new InputScoreEvent(this, matchId, principal.getName(), scoreDto));
        return "dashboard";
    }
}
