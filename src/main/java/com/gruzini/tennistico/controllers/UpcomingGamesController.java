package com.gruzini.tennistico.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/upcoming")
public class UpcomingGamesController {

    @GetMapping
    public String showUpcomingGamesPage(final Model model){
        return "upcoming";
    }
}
