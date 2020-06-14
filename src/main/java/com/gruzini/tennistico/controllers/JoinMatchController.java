package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.exceptions.MatchNotFoundException;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import com.gruzini.tennistico.services.JoinMatchService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/join-match")
public class JoinMatchController {

    private final JoinMatchService joinMatchService;

    public JoinMatchController(JoinMatchService joinMatchService) {
        this.joinMatchService = joinMatchService;
    }

    @GetMapping("/{match_id}")
    public String joinMatch(@PathVariable(name = "match_id") final Long matchId, final Principal principal) {
        joinMatchService.joinGuestToMatch(principal.getName(), matchId);
        //TODO send the notification to host
        return "dashboard";
    }

    //TODO modify handlers according to general decision
    //TODO move handlers to controller advice
    @ExceptionHandler(MatchNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String handleMatchNotFoundException(final Exception exception, final Model model) {
        model.addAttribute("expMessage", exception.getMessage());
        return "error-information";
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String handlePlayerNotFoundException(final Exception exception, final Model model){
        model.addAttribute("expMessage", exception.getMessage());
        return "error-information";
    }
}
