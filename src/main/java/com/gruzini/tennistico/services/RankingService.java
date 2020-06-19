package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.events.InputScoreEvent;
import com.gruzini.tennistico.services.entities.PlayerService;
import com.gruzini.tennistico.services.score.RankingPointCounter;
import com.gruzini.tennistico.services.score.WinDecider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    private final PlayerService playerService;
    private final MatchService matchService;
    private final RankingPointCounter rankingPointCounter;
    private final WinDecider winDecider;

    public RankingService(PlayerService playerService,
                          MatchService matchService, @Qualifier("simple") RankingPointCounter rankingPointCounter, WinDecider winDecider) {
        this.playerService = playerService;
        this.matchService = matchService;
        this.rankingPointCounter = rankingPointCounter;
        this.winDecider = winDecider;
    }

    /* TODO
     * determine the winner and loser
     * update players win/loss - in their rankings
     * calculate the to be added ranking points - STRATEGY
     * update player rankingPoints -> in their ranking
     * save players to db
     */

    @EventListener
    public void handleInputScoreEvent(final InputScoreEvent event) {
        final Match match = matchService.getById(event.getMatchId());
        updateWinner(winDecider.decideWinner(match));
        updateLoser(winDecider.decideLoser(match));
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
