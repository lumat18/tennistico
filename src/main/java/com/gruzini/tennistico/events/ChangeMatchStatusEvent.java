package com.gruzini.tennistico.events;

import com.gruzini.tennistico.domain.enums.MatchStatus;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

public class ChangeMatchStatusEvent extends ApplicationEvent {

    private final LocalDateTime expirationDateTime;
    private final MatchStatus currentMatchStatus;
    private final MatchStatus desiredMatchStatus;
    private final boolean checkedByStartingMatchTime;

    public ChangeMatchStatusEvent(Object source, LocalDateTime expirationDateTime,
                                  MatchStatus currentMatchStatus,
                                  MatchStatus desiredMatchStatus,
                                  boolean checkedByStartingMatchTime) {
        super(source);
        this.expirationDateTime = expirationDateTime;
        this.currentMatchStatus = currentMatchStatus;
        this.desiredMatchStatus = desiredMatchStatus;
        this.checkedByStartingMatchTime = checkedByStartingMatchTime;
    }

    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    public MatchStatus getCurrentMatchStatus() {
        return currentMatchStatus;
    }

    public MatchStatus getDesiredMatchStatus() {
        return desiredMatchStatus;
    }

    public boolean isCheckedByStartingMatchTime() {
        return checkedByStartingMatchTime;
    }
}
