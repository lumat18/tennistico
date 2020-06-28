package com.gruzini.tennistico.events;

import com.gruzini.tennistico.models.dto.ScoreDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class InputScoreEvent extends ApplicationEvent implements ConfirmEvent {

    private final Long matchId;
    private final Long triggerNotificationId;
    private final String username;
    private final ScoreDto scoreDto;

    public InputScoreEvent(Object source, Long matchId, Long triggerNotificationId, String username, ScoreDto scoreDto) {
        super(source);
        this.matchId = matchId;
        this.triggerNotificationId = triggerNotificationId;
        this.username = username;
        this.scoreDto = scoreDto;
    }
}
