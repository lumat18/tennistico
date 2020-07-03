package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.Score;
import com.gruzini.tennistico.events.ConfirmScoreEvent;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.PlayerService;
import com.gruzini.tennistico.services.point_counter.RankingPointCounter;
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
        updateStandings(match.getScore());
    }

    public void updateStandings(final Score score) {
        final Player winner = score.getWinner();
        final Player loser = score.getLoser();

        if (score.isDraw()){
            winner.setRankingPoints(rankingPointCounter.calculateDrawPoints(winner.getRankingPoints(), loser.getRankingPoints()));
            loser.setRankingPoints(rankingPointCounter.calculateDrawPoints(loser.getRankingPoints(), winner.getRankingPoints()));
        } else {
            winner.setRankingPoints(rankingPointCounter.calculateWinPoints(winner.getRankingPoints(), loser.getRankingPoints()));
            loser.setRankingPoints(rankingPointCounter.calculateLossPoints(loser.getRankingPoints(), winner.getRankingPoints()));
        }

        playerService.save(winner);
        playerService.save(loser);
    }
}
