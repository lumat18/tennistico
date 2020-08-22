package com.gruzini.tennistico.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RejectScoreEvent extends ApplicationEvent implements ConfirmableEvent{

    private final Long matchId;
    private final Long triggerNotificationId;
    private final String username;

    public RejectScoreEvent(Object source, Long matchId, Long triggerNotificationId, String username) {
        super(source);
        this.matchId = matchId;
        this.triggerNotificationId = triggerNotificationId;
        this.username = username;
    }
}
