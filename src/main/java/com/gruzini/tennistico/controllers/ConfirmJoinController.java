package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.events.ConfirmJoinEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/confirm-join")
public class ConfirmJoinController {

    private final ApplicationEventPublisher applicationEventPublisher;

    public ConfirmJoinController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostMapping
    public String confirmGuestJoiningMatch(@RequestParam(name = "match_id") final Long matchId, final Principal principal) {
        applicationEventPublisher.publishEvent(new ConfirmJoinEvent(this, matchId, principal.getName()));
        return "redirect:/dashboard";
    }
}
