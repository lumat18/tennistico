package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.events.ConfirmScoreEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/dashboard/confirm-score")
public class ConfirmScoreController {

    private final ApplicationEventPublisher applicationEventPublisher;

    public ConfirmScoreController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping("/{match_id}")
    public String confirmScoreSetByHost(@PathVariable(name = "match_id") final Long matchId,
                                        final Principal principal) {
        applicationEventPublisher.publishEvent(new ConfirmScoreEvent(this, matchId, principal.getName()));
        return "redirect:/dashboard";
    }
}
