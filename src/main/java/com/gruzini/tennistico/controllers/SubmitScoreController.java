package com.gruzini.tennistico.controllers;

<<<<<<< HEAD
import com.gruzini.tennistico.services.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> score-input
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/submit-score")
public class SubmitScoreController {

<<<<<<< HEAD
   private final MatchService matchService;

   public SubmitScoreController(final MatchService matchService) {
      this.matchService = matchService;
   }

   @GetMapping("/{match_id}")
   public String showSubmitScorePage(@PathVariable(name = "match_id")final Long matchId){
      matchService.getById(matchId);
=======
   @GetMapping
   public String showSubmitScorePage(){
>>>>>>> score-input
      return "score";
   }
}
