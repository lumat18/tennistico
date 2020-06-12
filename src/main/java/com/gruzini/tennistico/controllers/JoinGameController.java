package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.exceptions.GameNotFoundException;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import com.gruzini.tennistico.services.HostedGameService;
import com.gruzini.tennistico.services.JoinGameService;
import com.gruzini.tennistico.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/join-game")
public class JoinGameController {

    private final JoinGameService joinGameService;

    public JoinGameController(HostedGameService hostedGameService, PlayerService playerService, JoinGameService joinGameService) {
        this.joinGameService = joinGameService;
    }

    @GetMapping("/{game_id}")
    public String joinGame(@PathVariable(name = "game_id") final Long gameId, final Principal principal){
        joinGameService.joinGuestToGame(principal.getName(), gameId);
        //TODO send the notification to host
        return "dashboard";
    }

    //TODO modify handlers according to general decision
    //TODO move handlers to controller advice
    @ExceptionHandler(GameNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String handleGameNotFoundException(final Exception exception, final Model model){
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
