package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.services.GameService;
import com.gruzini.tennistico.services.JoinConfirmService;
import com.gruzini.tennistico.services.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/confirm-join")
public class ConfirmJoinController {

    private final JoinConfirmService joinConfirmService;

    public ConfirmJoinController(PlayerService playerService, GameService gameService, JoinConfirmService joinConfirmService) {
        this.joinConfirmService = joinConfirmService;
    }

    @GetMapping("/{game_id}")
    public String confirmGuestJoiningTheGame(@PathVariable(name = "game_id") final Long gameId, final Principal principal) {
        joinConfirmService.confirmJoin(gameId, principal.getName());
        //TODO wysłać notification do Guesta
        return "dashboard";
    }
}
