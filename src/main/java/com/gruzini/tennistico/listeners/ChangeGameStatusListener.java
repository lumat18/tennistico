package com.gruzini.tennistico.listeners;

import com.gruzini.tennistico.events.ChangeGameStatusEvent;
import com.gruzini.tennistico.services.GameService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ChangeGameStatusListener {

    private final GameService gameService;

    public ChangeGameStatusListener(GameService gameService) {
        this.gameService = gameService;
    }

    @Async
    @EventListener
    public synchronized void handleEventPublish(ChangeGameStatusEvent event) {
        gameService.updateExpiredGamesStatus(event.getExpirationDateTime(), event.getCurrentGameStatus(), event.getDesiredGameStatus());
    }
}
