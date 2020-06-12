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

    private final UpcomingGameService upcomingGameService;
    private final HostedGameService hostedGameService;
    private final JoinRequestGameService joinRequestGameService;

    public FutureGameChecker(UpcomingGameService upcomingGameService, HostedGameService hostedGameService, JoinRequestGameService joinRequestGameService) {
        this.upcomingGameService = upcomingGameService;
        this.hostedGameService = hostedGameService;
        this.joinRequestGameService = joinRequestGameService;
    }

    @Scheduled(cron = "0 0/15 * * * *")
    public void updateFutureGamesStatus() {
        changeGameStatusFromUpcomingToPending();
        changeGameStatusFromHostedToBusted();
        changeGameStatusFromJoinRequestToBusted();
    }

    private void changeGameStatusFromUpcomingToPending() {
        final List<Game> games = upcomingGameService.getAllGamesThatPassed();
        games.forEach(game -> game.setGameStatus(GameStatus.PENDING));
    }

    private void changeGameStatusFromHostedToBusted() {
        final List<Game> games = hostedGameService.getAllGamesThatPassed();
        hostedGameService.changeGameStatusTo(games, GameStatus.BUSTED);
    }

    private void changeGameStatusFromJoinRequestToBusted() {
        final List<Game> games = joinRequestGameService.getAllGamesThatPassed();
        joinRequestGameService.changeGameStatusTo(games, GameStatus.BUSTED);
    }
}
