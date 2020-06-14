package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.services.ConfirmJoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/confirm-join")
public class ConfirmJoinController {

    private final ConfirmJoinService confirmJoinService;

    public ConfirmJoinController(ConfirmJoinService confirmJoinService) {
        this.confirmJoinService = confirmJoinService;
    }

    @GetMapping("/{match_id}")
    public String confirmGuestJoiningMatch(@PathVariable(name = "match_id") final Long matchId, final Principal principal) {
        confirmJoinService.confirmJoin(matchId, principal.getName());
        //TODO wysłać notification do Guesta
        return "dashboard";
    }
}
