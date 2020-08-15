package com.gruzini.tennistico.events;

import com.gruzini.tennistico.models.dto.ScoreDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class InputScoreEvent extends ApplicationEvent implements ConfirmableEvent {

    private final Long matchId;
    private final String username;
    private final ScoreDto scoreDto;
    private final Long triggerNotificationId;

    public InputScoreEvent(Object source, Long matchId, String username, ScoreDto scoreDto, Long triggerNotificationId) {
        super(source);
        this.matchId = matchId;
        this.username = username;
        this.scoreDto = scoreDto;
        this.triggerNotificationId = triggerNotificationId;
    }
}
