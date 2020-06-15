package com.gruzini.tennistico.events;

import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.publishers.DateTimeSelector;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

public class ChangeMatchStatusEvent extends ApplicationEvent {

    private final LocalDateTime expirationDateTime;
    private final MatchStatus currentMatchStatus;
    private final MatchStatus desiredMatchStatus;
    private final DateTimeSelector dateTimeSelector;

    public ChangeMatchStatusEvent(Object source, LocalDateTime expirationDateTime,
                                  MatchStatus currentMatchStatus,
                                  MatchStatus desiredMatchStatus,
                                  DateTimeSelector dateTimeSelector) {
        super(source);
        this.expirationDateTime = expirationDateTime;
        this.currentMatchStatus = currentMatchStatus;
        this.desiredMatchStatus = desiredMatchStatus;
        this.dateTimeSelector = dateTimeSelector;
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

    public DateTimeSelector getDateTimeSelector() {
        return dateTimeSelector;
    }
}
