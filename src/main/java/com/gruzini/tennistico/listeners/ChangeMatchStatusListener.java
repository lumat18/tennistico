package com.gruzini.tennistico.listeners;

import com.gruzini.tennistico.events.ChangeMatchStatusByEndingDateTimeEvent;
import com.gruzini.tennistico.events.ChangeMatchStatusByStartingDateTimeEvent;
import com.gruzini.tennistico.services.entities.MatchService;
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
    public synchronized void handleEventPublishByStartingDateTime(ChangeMatchStatusByStartingDateTimeEvent event) {
        matchService.updateExpiredMatchesStatusByStartingDateTime(event);
    }

    @Async
    @EventListener
    public synchronized void handleEventPublishByEndingDateTime(ChangeMatchStatusByEndingDateTimeEvent event) {
        matchService.updateExpiredMatchesStatusByEndingDateTime(event);
    }
}
