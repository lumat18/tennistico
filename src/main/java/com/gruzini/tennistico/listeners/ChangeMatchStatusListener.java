package com.gruzini.tennistico.listeners;

import com.gruzini.tennistico.events.ChangeMatchStatusEvent;
import com.gruzini.tennistico.services.MatchService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ChangeMatchStatusListener {

    private final MatchService matchService;

    public ChangeMatchStatusListener(MatchService matchService) {
        this.matchService = matchService;
    }

    @Async
    @EventListener
    public synchronized void handleEventPublish(ChangeMatchStatusEvent event) {
        matchService.updateExpiredMatchesStatus(event.getExpirationDateTime(), event.getCurrentMatchStatus(), event.getDesiredMatchStatus());
    }
}
