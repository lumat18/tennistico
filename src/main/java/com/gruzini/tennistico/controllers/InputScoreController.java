package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.mappers.ScoreMapper;
import com.gruzini.tennistico.models.dto.ScoreDto;
import com.gruzini.tennistico.services.InputScoreService;
import com.gruzini.tennistico.services.entities.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/input-score")
public class InputScoreController {

   private final InputScoreService inputScoreService;
   private final MatchService matchService;
   private final ScoreMapper scoreMapper;

   public InputScoreController(final InputScoreService inputScoreService,
                               final MatchService matchService,
                               final ScoreMapper scoreMapper) {
      this.inputScoreService = inputScoreService;
      this.matchService = matchService;
      this.scoreMapper = scoreMapper;
   }

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
                                       final Model model) {
      if (errors.hasErrors()) {
         model.addAttribute("match", matchService.getById(matchId));
         model.addAttribute("score", new ScoreDto());
         model.addAttribute("msg", "Invalid score input");
         return "score";
      }

        inputScoreService.inputScore(matchId, scoreDTO);
        return "dashboard";
    }
}
