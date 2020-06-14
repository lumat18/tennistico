package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.services.ConfirmJoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/confirm-join")
public class ConfirmJoinController {

    private final ConfirmJoinService confirmJoinService;

    public ConfirmJoinController(ConfirmJoinService confirmJoinService) {
        this.confirmJoinService = confirmJoinService;
    }

    @PostMapping
    public String confirmGuestJoiningMatch(@RequestParam(name = "match_id") final Long matchId, final Principal principal) {
        confirmJoinService.confirmJoin(matchId, principal.getName());
        //TODO wysłać notification do Guesta
        return "dashboard";
    }
}
