package com.gruzini.tennistico.events;

import com.gruzini.tennistico.domain.enums.GameStatus;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

public class ChangeGameStatusEvent extends ApplicationEvent {

    private final LocalDateTime expirationDateTime;
    private final GameStatus currentGameStatus;
    private final GameStatus desiredGameStatus;

    public ChangeGameStatusEvent(Object source, LocalDateTime expirationDateTime, GameStatus currentGameStatus, GameStatus desiredGameStatus) {
        super(source);
        this.expirationDateTime = expirationDateTime;
        this.currentGameStatus = currentGameStatus;
        this.desiredGameStatus = desiredGameStatus;
    }

    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    public GameStatus getCurrentGameStatus() {
        return currentGameStatus;
    }

    public GameStatus getDesiredGameStatus() {
        return desiredGameStatus;
    }
}
