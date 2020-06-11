package com.gruzini.tennistico.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/games-history")
public class GamesHistoryController {
   @GetMapping
   public String showGamesHistoryPage(){
      return "dashboard/games-history";
   }
}
