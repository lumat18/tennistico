package com.gruzini.tennistico.scheduler;

import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.events.ChangeMatchStatusByEndingDateTimeEvent;
import com.gruzini.tennistico.events.ChangeMatchStatusByStartingDateTimeEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MatchStatusChangePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public MatchStatusChangePublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Scheduled(cron = "0 0/15 * * * *")
    public void changeStatusFromUpcomingToPending() {
        final ChangeMatchStatusByStartingDateTimeEvent event =
                new ChangeMatchStatusByStartingDateTimeEvent(this, MatchStatus.UPCOMING, MatchStatus.PENDING);
        applicationEventPublisher.publishEvent(event);
    }

    @Scheduled(cron = "0 0/15 * * * *")
    public void changeStatusFromJoinRequestToBusted() {
        final ChangeMatchStatusByStartingDateTimeEvent event =
                new ChangeMatchStatusByStartingDateTimeEvent(this, MatchStatus.JOIN_REQUEST, MatchStatus.BUSTED);
        applicationEventPublisher.publishEvent(event);
    }

    @Scheduled(cron = "0 0/15 * * * *")
    public void changeStatusFromHostedToBusted() {
        final ChangeMatchStatusByStartingDateTimeEvent event =
                new ChangeMatchStatusByStartingDateTimeEvent(this, MatchStatus.HOSTED, MatchStatus.BUSTED);
        applicationEventPublisher.publishEvent(event);
    }

    @Scheduled(cron = "0 0 * * * *")
    public void changeStatusFromScoreToBeConfirmedToBusted() {
        final ChangeMatchStatusByEndingDateTimeEvent event =
                new ChangeMatchStatusByEndingDateTimeEvent(this, MatchStatus.SCORE_TO_BE_CONFIRMED, MatchStatus.BUSTED);
        applicationEventPublisher.publishEvent(event);
    }

    @Scheduled(cron = "0 0 * * * *")
    public void changeStatusFromPendingToBusted() {
        final ChangeMatchStatusByEndingDateTimeEvent event =
                new ChangeMatchStatusByEndingDateTimeEvent(this, MatchStatus.PENDING, MatchStatus.BUSTED);
        applicationEventPublisher.publishEvent(event);
    }
}
