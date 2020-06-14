package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.services.ConfirmJoinService;
import com.gruzini.tennistico.services.GameService;
import com.gruzini.tennistico.services.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/confirm-join")
public class ConfirmJoinController {

    private final ConfirmJoinService confirmJoinService;

    public ConfirmJoinController(PlayerService playerService, GameService gameService, ConfirmJoinService confirmJoinService) {
        this.confirmJoinService = confirmJoinService;
    }

    @GetMapping("/{game_id}")
    public String confirmGuestJoiningTheGame(@PathVariable(name = "game_id") final Long gameId, final Principal principal) {
        confirmJoinService.confirmJoin(gameId, principal.getName());
        //TODO wysłać notification do Guesta
        return "dashboard";
    }
}
