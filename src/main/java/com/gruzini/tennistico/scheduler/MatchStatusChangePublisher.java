package com.gruzini.tennistico.scheduler;

import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.events.ChangeMatchStatusByEndingDateTimeEvent;
import com.gruzini.tennistico.events.ChangeMatchStatusByStartingDateTimeEvent;
import com.gruzini.tennistico.events.HeldMatchEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MatchStatusChangePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public MatchStatusChangePublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    //TODO: discuss scheduling for this event, for now used cron.upcoming
    @Scheduled(cron = "${scheduling.cron.upcoming}")
    public void publishHeldGameEvent() {
        applicationEventPublisher.publishEvent(new HeldMatchEvent(this));
    }

    @Scheduled(cron = "${scheduling.cron.upcoming}")
    public void changeStatusFromJoinRequestToBusted() {
        final ChangeMatchStatusByStartingDateTimeEvent event =
                new ChangeMatchStatusByStartingDateTimeEvent(this, MatchStatus.JOIN_REQUEST, MatchStatus.BUSTED);
        applicationEventPublisher.publishEvent(event);
    }

    @Scheduled(cron = "${scheduling.cron.upcoming}")
    public void changeStatusFromHostedToBusted() {
        final ChangeMatchStatusByStartingDateTimeEvent event =
                new ChangeMatchStatusByStartingDateTimeEvent(this, MatchStatus.HOSTED, MatchStatus.BUSTED);
        applicationEventPublisher.publishEvent(event);
    }

    @Scheduled(cron = "${scheduling.cron.pending}")
    public void changeStatusFromScoreToBeConfirmedToBusted() {
        final ChangeMatchStatusByEndingDateTimeEvent event =
                new ChangeMatchStatusByEndingDateTimeEvent(this, MatchStatus.SCORE_TO_BE_CONFIRMED, MatchStatus.BUSTED);
        applicationEventPublisher.publishEvent(event);
    }

    @Scheduled(cron = "${scheduling.cron.pending}")
    public void changeStatusFromPendingToBusted() {
        final ChangeMatchStatusByEndingDateTimeEvent event =
                new ChangeMatchStatusByEndingDateTimeEvent(this, MatchStatus.PENDING, MatchStatus.BUSTED);
        applicationEventPublisher.publishEvent(event);
    }
}
