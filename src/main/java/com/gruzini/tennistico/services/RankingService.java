package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.services.entities.PlayerService;
import com.gruzini.tennistico.services.score.RankingPointCounter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    private final PlayerService playerService;
    private final RankingPointCounter rankingPointCounter;

    public RankingService(PlayerService playerService,
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
        player.setRankingPoints(player.getRankingPoints() + rankingPointCounter.calculateWinPoints());
        playerService.save(player);
    }

    public void updateLoser(final Player player) {
        player.setRankingPoints(player.getRankingPoints() + rankingPointCounter.calculateLossPoints());
        playerService.save(player);
    }
}
