package com.gruzini.tennistico.scheduler;

import com.gruzini.tennistico.events.ChangeMatchStatusByEndingDateTimeEvent;
import com.gruzini.tennistico.events.ChangeMatchStatusByStartingDateTimeEvent;
import com.gruzini.tennistico.events.HeldMatchEvent;
import com.gruzini.tennistico.services.entity_related.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MatchStatusChangeListener {

    private final MatchService matchService;

    public MatchStatusChangeListener(MatchService matchService) {
        this.matchService = matchService;
    }

    @Async
    @EventListener
    @Order(1)
    public synchronized void handleHeldMatchEvent(final HeldMatchEvent event) {
        matchService.updateExpiredMatchesStatusByStartingDateTime(event.getCurrentStatus(), event.getDesiredStatus());
        log.info("All matches with two players that started are now pending");
    }

    @Async
    @EventListener
    public synchronized void handleEventPublishByStartingDateTime(ChangeMatchStatusByStartingDateTimeEvent event) {
        matchService.updateExpiredMatchesStatusByStartingDateTime(event.getCurrentMatchStatus(), event.getDesiredMatchStatus());
        log.info("All not joined matches that date passed were busted");
    }

    @Async
    @EventListener
    public synchronized void handleEventPublishByEndingDateTime(ChangeMatchStatusByEndingDateTimeEvent event) {
        matchService.updateExpiredMatchesStatusByEndingDateTime(event);
        log.info("All not confirmed matches that date passed were busted");
    }
}
