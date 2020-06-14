package com.gruzini.tennistico.checkers;

import org.springframework.stereotype.Component;

@Component
public class FutureGameChecker {

//    private final UpcomingGameService upcomingGameService;
//    private final HostedGameService hostedGameService;
//    private final JoinRequestGameService joinRequestGameService;
//
//    public FutureGameChecker(UpcomingGameService upcomingGameService, HostedGameService hostedGameService, JoinRequestGameService joinRequestGameService) {
//        this.upcomingGameService = upcomingGameService;
//        this.hostedGameService = hostedGameService;
//        this.joinRequestGameService = joinRequestGameService;
//    }
//
//    @Scheduled(cron = "0 0/15 * * * *")
//    public void updateFutureGamesStatus() {
//        changeGameStatusFromUpcomingToPending();
//        changeGameStatusFromHostedToBusted();
//        changeGameStatusFromJoinRequestToBusted();
//    }
//
//    private void changeGameStatusFromUpcomingToPending() {
//        final List<Game> games = upcomingGameService.getAllGamesThatPassed();
//        upcomingGameService.changeGameStatusTo(games, GameStatus.PENDING);
//    }
//
//    private void changeGameStatusFromHostedToBusted() {
//        final List<Game> games = hostedGameService.getAllGamesThatPassed();
//        hostedGameService.changeGameStatusTo(games, GameStatus.BUSTED);
//    }
//
//    private void changeGameStatusFromJoinRequestToBusted() {
//        final List<Game> games = joinRequestGameService.getAllGamesThatPassed();
//        joinRequestGameService.changeGameStatusTo(games, GameStatus.BUSTED);
//    }
}
