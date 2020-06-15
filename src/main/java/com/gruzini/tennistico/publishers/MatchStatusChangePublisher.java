package com.gruzini.tennistico.publishers;

import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.events.ChangeMatchStatusEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MatchStatusChangePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public MatchStatusChangePublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Scheduled(cron = "0 0/15 * * * *")
    public void changeStatusFromUpcomingToPending() {
        final ChangeMatchStatusEvent event =
                new ChangeMatchStatusEvent(this, LocalDateTime.now(), MatchStatus.UPCOMING, MatchStatus.PENDING, true);
        applicationEventPublisher.publishEvent(event);
    }

    @Scheduled(cron = "0 0/15 * * * *")
    public void changeStatusFromJoinRequestToBusted() {
        final ChangeMatchStatusEvent event =
                new ChangeMatchStatusEvent(this, LocalDateTime.now(), MatchStatus.JOIN_REQUEST, MatchStatus.BUSTED, true);
        applicationEventPublisher.publishEvent(event);
    }

    @Scheduled(cron = "0 0/15 * * * *")
    public void changeStatusFromHostedToBusted() {
        final ChangeMatchStatusEvent event =
                new ChangeMatchStatusEvent(this, LocalDateTime.now(), MatchStatus.HOSTED, MatchStatus.BUSTED, true);
        applicationEventPublisher.publishEvent(event);
    }

    @Scheduled(cron = "0 0 * * * *")
    public void changeStatusFromScoreToBeConfirmedToBusted() {
        final ChangeMatchStatusEvent event =
                new ChangeMatchStatusEvent(this, LocalDateTime.now().minusDays(7), MatchStatus.SCORE_TO_BE_CONFIRMED, MatchStatus.BUSTED, false);
        applicationEventPublisher.publishEvent(event);
    }

    @Scheduled(cron = "0 0 * * * *")
    public void changeStatusFromPendingToBusted() {
        final ChangeMatchStatusEvent event =
                new ChangeMatchStatusEvent(this, LocalDateTime.now().minusDays(7), MatchStatus.PENDING, MatchStatus.BUSTED, false);
        applicationEventPublisher.publishEvent(event);
    }
}
