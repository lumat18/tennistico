package com.gruzini.tennistico.events;

import com.gruzini.tennistico.domain.enums.MatchStatus;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class HeldMatchEvent extends ApplicationEvent {

    private final MatchStatus currentStatus = MatchStatus.UPCOMING;
    private final MatchStatus desiredStatus = MatchStatus.PENDING;

    public HeldMatchEvent(Object source) {
        super(source);
    }
}
