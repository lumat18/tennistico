package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.events.ConfirmScoreEvent;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.PlayerService;
import com.gruzini.tennistico.services.score.RankingPointCounter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    private final PlayerService playerService;
    private final MatchService matchService;
    private final RankingPointCounter rankingPointCounter;

    public RankingService(PlayerService playerService,
                          MatchService matchService,
                          @Qualifier("simple") RankingPointCounter rankingPointCounter) {
        this.playerService = playerService;
        this.matchService = matchService;
        this.rankingPointCounter = rankingPointCounter;
    }

    /* TODO
     * determine the winner and loser
     * update players win/loss - in their rankings
     * calculate the to be added ranking points - STRATEGY
     * update player rankingPoints -> in their ranking
     * save players to db
     */

    @EventListener
    public void handleEvent(final ConfirmScoreEvent event) {
        final Match match = matchService.getById(event.getMatchId());
        updateWinner(match.getScore().getWinner());
        updateLoser(match.getScore().getLoser());
    }

    public void updateWinner(final Player player) {
        player.setRankingPoints(player.getRankingPoints() + rankingPointCounter.calculateWinPoints());
        playerService.save(player);
    }

    public void updateLoser(final Player player) {
        player.setRankingPoints(player.getRankingPoints() + rankingPointCounter.calculateLossPoints());
        playerService.save(player);
    }
}
