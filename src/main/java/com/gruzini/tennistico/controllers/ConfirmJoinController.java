package com.gruzini.tennistico.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/confirm-join")
public class ConfirmJoinController {

    @GetMapping("/{game_id}")
    public String confirmGuestJoiningTheGame(@PathVariable(name = "game_id") final Long gameId) {
        //TODO wyciągnąć grę, ewentualnie sprawdzić, czy są 2 gracze i zmienić status na UPCOMING
        //TODO wysłać notification do Guesta
        return "dashboard";
    }
}
