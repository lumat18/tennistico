package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.events.RejectScoreEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/reject-score")
public class RejectScoreController {

    private final ApplicationEventPublisher applicationEventPublisher;

    public RejectScoreController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostMapping
    public String handleScoreRejection(@RequestParam(name = "match_id") final Long matchId,
                                       @RequestParam(name = "trigger_id") final Long triggerNotificationId,
                                       final Principal principal){
        applicationEventPublisher.publishEvent(new RejectScoreEvent(this, matchId, triggerNotificationId, principal.getName()));
        return "redirect:/dashboard";
    }
}
