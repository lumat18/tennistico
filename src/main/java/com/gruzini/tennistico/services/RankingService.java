package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.Score;
import com.gruzini.tennistico.events.ConfirmScoreEvent;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.PlayerService;
import com.gruzini.tennistico.services.entity_related.ScoreService;
import com.gruzini.tennistico.services.point_counter.RankingPointCounter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    private final MatchService matchService;
    private final ScoreService scoreService;
    private final PlayerService playerService;
    private final RankingPointCounter rankingPointCounter;
    private final WinDecidingService winDecidingService;

    public RankingService(final MatchService matchService,
                          final ScoreService scoreService,
                          final PlayerService playerService,
                          @Qualifier("elo") final RankingPointCounter rankingPointCounter,
                          final WinDecidingService winDecidingService) {
        this.scoreService = scoreService;
        this.matchService = matchService;
        this.playerService = playerService;
        this.rankingPointCounter = rankingPointCounter;
        this.winDecidingService = winDecidingService;
    }

    @EventListener
    public void handleEvent(final ConfirmScoreEvent event) {
        final Match match = matchService.getById(event.getMatchId());
        match.setScore(winDecidingService.updateWinnerAndLoser(match));
        updateStandings(match.getScore());
        matchService.save(match);
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
        scoreService.save(score);
    }
}
