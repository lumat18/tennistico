package com.gruzini.tennistico.checkers;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.services.HostedGameService;
import com.gruzini.tennistico.services.JoinRequestGameService;
import com.gruzini.tennistico.services.UpcomingGameService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FutureGameChecker {
    //TODO co 15min szuka gier, których data startu już była
    //TODO jeżeli mają status UPCOMING to zmienia na PENDING
    //TODO jeżeli mają status HOSTED lub JOIN_REQUEST to zmienia na BUSTED

    private final UpcomingGameService upcomingGameService;
    private final HostedGameService hostedGameService;
    private final JoinRequestGameService joinRequestGameService;

    public FutureGameChecker(UpcomingGameService upcomingGameService, HostedGameService hostedGameService, JoinRequestGameService joinRequestGameService) {
        this.upcomingGameService = upcomingGameService;
        this.hostedGameService = hostedGameService;
        this.joinRequestGameService = joinRequestGameService;
    }

    @Scheduled(cron = "* */15 * * * *")
    public void updateFutureGamesStatus() {
        changeGameStatusFromUpcomingToPending();
        changeGameStatusFromHostedToBusted();
        changeGameStatusFromJoinRequestToBusted();
    }

    private void changeGameStatusFromUpcomingToPending() {
        final List<Game> games = upcomingGameService.getAllUpcomingGamesThatPassed();
        games.forEach(game -> game.setGameStatus(GameStatus.PENDING));
    }

    private void changeGameStatusFromHostedToBusted() {
        final List<Game> games = hostedGameService.getAllHostedGamesThatPassed();
        games.forEach(game -> game.setGameStatus(GameStatus.BUSTED));
    }

    private void changeGameStatusFromJoinRequestToBusted() {
        final List<Game> games = joinRequestGameService.getAllJoinRequestGamesThatPassed();
        games.forEach(game -> game.setGameStatus(GameStatus.BUSTED));
    }
}
