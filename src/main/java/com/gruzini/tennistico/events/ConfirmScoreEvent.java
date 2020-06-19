package com.gruzini.tennistico.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ConfirmScoreEvent extends ApplicationEvent {

    private final Long matchId;
    private final String username;

    public ConfirmScoreEvent(Object source, Long matchId, String username) {
        super(source);
        this.matchId = matchId;
        this.username = username;
    }
}
