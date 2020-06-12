package com.gruzini.tennistico.checkers;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.services.HostedGameService;
import com.gruzini.tennistico.services.UpcomingGameService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FutureGameChecker {
    //TODO co 15min szuka gier, których data startu już była
    //TODO jeżeli mają status UPCOMING to zmienia na PENDING
    //TODO jeżeli mają status HOSTED lub JOIN_REQUEST to zmienia na BUSTED

    private final UpcomingGameService upcomingGameService;
    private final HostedGameService hostedGameService;

    public FutureGameChecker(UpcomingGameService upcomingGameService, HostedGameService hostedGameService) {
        this.upcomingGameService = upcomingGameService;
        this.hostedGameService = hostedGameService;
    }

    public void changeGameStatusFromUpcomingToPending(){
        final List<Game> games = upcomingGameService.getAllUpcomingGamesThatPassed();
        games.forEach(game -> game.setGameStatus(GameStatus.PENDING));
    }

}
