package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.events.ConfirmScoreEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/confirm-score")
public class ConfirmScoreController {

    private final ApplicationEventPublisher applicationEventPublisher;

    public ConfirmScoreController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostMapping
    public String confirmScoreSetByHost(@RequestParam(name = "match_id") final Long matchId,
                                        @RequestParam(name = "trigger_id") final Long triggerNotificationId,
                                        final Principal principal) {
        applicationEventPublisher.publishEvent(new ConfirmScoreEvent(this, matchId, triggerNotificationId, principal.getName()));
        return "redirect:/dashboard";
    }
}
