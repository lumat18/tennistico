package com.gruzini.tennistico.events;

import com.gruzini.tennistico.domain.enums.GameStatus;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GameStatusChangePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public GameStatusChangePublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Scheduled(cron = "0/15 * * * * *")
    public void changeStatusFromUpcomingToPending() {
        final ChangeGameStatusEvent event =
                new ChangeGameStatusEvent(this, LocalDateTime.now(), GameStatus.UPCOMING, GameStatus.PENDING);
        applicationEventPublisher.publishEvent(event);
    }

    @Scheduled(cron = "0/15 * * * * *")
    public void changeStatusFromPendingToBusted() {
        final ChangeGameStatusEvent event =
                new ChangeGameStatusEvent(this, LocalDateTime.now().plusDays(7), GameStatus.PENDING, GameStatus.BUSTED);
        applicationEventPublisher.publishEvent(event);
    }
}
