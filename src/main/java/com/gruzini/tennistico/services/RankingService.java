package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.Ranking;
import com.gruzini.tennistico.models.score.WinDecider;

public class RankingService {

    private final PlayerService playerService;
    private final WinDecider winDecider;

    public RankingService(PlayerService playerService, WinDecider winDecider) {
        this.playerService = playerService;
        this.winDecider = winDecider;
    }

    /* TODO
     * determine the winner and loser
     * update players win/loss - in their rankings
     * calculate the to be added ranking points - STRATEGY
     * update player rankingPoints -> in their ranking
     * save players to db
     */

    public void updateRanking(final Match match) {
        accumulateWins(match);
        accumulateLosses(match);

        //calculate ranking points


        match.getPlayers().forEach(playerService::save);
    }

    private void accumulateWins(Match match) {
        final Player winner = winDecider.decideWinner(match);
        final Ranking winnerRanking = winner.getRanking();
        winnerRanking.setWins(winnerRanking.getWins() + 1);
    }

    private void accumulateLosses(Match match) {
        final Player loser = winDecider.decideLoser(match);
        final Ranking loserRanking = loser.getRanking();
        loserRanking.setLosses(loserRanking.getLosses() + 1);
    }
}
