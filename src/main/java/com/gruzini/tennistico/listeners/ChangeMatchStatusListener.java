package com.gruzini.tennistico.listeners;

import com.gruzini.tennistico.events.ChangeMatchStatusByEndingDateTimeEvent;
import com.gruzini.tennistico.events.ChangeMatchStatusByStartingDateTimeEvent;
import com.gruzini.tennistico.services.entity_related.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ChangeMatchStatusListener {

    private final MatchService matchService;

    public ChangeMatchStatusListener(MatchService matchService) {
        this.matchService = matchService;
    }

    @Async
    @EventListener
    public synchronized void handleEventPublishByStartingDateTime(ChangeMatchStatusByStartingDateTimeEvent event) {
        matchService.updateExpiredMatchesStatusByStartingDateTime(event);
        log.info("All not joined matches that date passed were busted");
    }

    @Async
    @EventListener
    public synchronized void handleEventPublishByEndingDateTime(ChangeMatchStatusByEndingDateTimeEvent event) {
        matchService.updateExpiredMatchesStatusByEndingDateTime(event);
        log.info("All not confirmed matches that date passed were busted");
    }
}
