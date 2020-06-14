package com.gruzini.tennistico.events;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ChangeGameStatusListener {

//    private final GameService gameService;

    @Async
    @EventListener
    public synchronized void handleEventPublish(ChangeGameStatusEvent changeGameStatusEvent) {
        //TODO create generic GameService
//        List<Game> games = gameService.findExpierdGamesByStatus(changeGameStatusEvent.getExpirationDateTime(), changeGameStatusEvent.getCurrentGameStatus());
//        games.forEach(game -> {
//            game.setGameStatus(changeGameStatusEvent.getDesiredGameStatus());
//            gameService.save(game);
//        });
        System.out.println("Event published" + changeGameStatusEvent.getCurrentGameStatus());
    }
}
