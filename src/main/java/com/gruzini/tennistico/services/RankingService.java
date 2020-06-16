package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.score.WinDecider;

public class RankingService {

    private final WinDecider winDecider;

    public RankingService(WinDecider winDecider) {
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
        final Player winner = winDecider.decideWinner(match);
    }
}
