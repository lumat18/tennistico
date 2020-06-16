package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.Ranking;
import com.gruzini.tennistico.models.score.RankingPointCounter;
import com.gruzini.tennistico.models.score.WinDecider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    private final PlayerService playerService;
    private final RankingPointCounter rankingPointCounter;

    public RankingService(PlayerService playerService,
                          WinDecider winDecider,
                          @Qualifier("simple") RankingPointCounter rankingPointCounter) {
        this.playerService = playerService;
        this.rankingPointCounter = rankingPointCounter;
    }

    /* TODO
     * determine the winner and loser
     * update players win/loss - in their rankings
     * calculate the to be added ranking points - STRATEGY
     * update player rankingPoints -> in their ranking
     * save players to db
     */

    public void updateWinner(final Player player) {
        updateWinnerRanking(player.getRanking());
        playerService.save(player);
    }

    public void updateLoser(final Player player) {
        updateLoserRanking(player.getRanking());
        playerService.save(player);
    }

    private void updateWinnerRanking(final Ranking ranking) {
        accumulateWins(ranking);
        accumulateWinnerRankingPoints(ranking);
    }

    private void accumulateWins(final Ranking ranking) {
        ranking.setWins(ranking.getWins() + 1);
    }

    private void accumulateWinnerRankingPoints(final Ranking ranking) {
        final int rankingPoints = ranking.getRankingPoints();
        final int winPoints = rankingPointCounter.calculateWinPoints();
        ranking.setRankingPoints(rankingPoints + winPoints);
    }

    private void updateLoserRanking(final Ranking ranking) {
        accumulateLosses(ranking);
        accumulateLoserRankingPoints(ranking);
    }

    private void accumulateLosses(final Ranking ranking) {
        ranking.setLosses(ranking.getLosses() + 1);
    }

    private void accumulateLoserRankingPoints(final Ranking ranking) {
        final int rankingPoints = ranking.getRankingPoints();
        final int lossPoints = rankingPointCounter.calculateLossPoints();
        ranking.setRankingPoints(rankingPoints + lossPoints);
    }
}
