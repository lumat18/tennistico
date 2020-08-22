package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.events.RejectJoinEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/reject-join")
public class RejectJoinController {

    private final ApplicationEventPublisher applicationEventPublisher;

    public RejectJoinController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostMapping
    public String rejectGuestJoiningMatch(@RequestParam(name = "match_id") final Long matchId,
                                          @RequestParam(name = "trigger_id") final Long triggerNotificationId,
                                          final Principal principal) {
        applicationEventPublisher.publishEvent(new RejectJoinEvent(this, matchId, triggerNotificationId, principal.getName()));
        return "redirect:/dashboard";
    }
}
