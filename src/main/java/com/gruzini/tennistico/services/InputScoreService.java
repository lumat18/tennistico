package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.services.entities.MatchService;
import com.gruzini.tennistico.services.score.WinDecider;
import org.springframework.stereotype.Service;

@Service
public class InputScoreService {

    private final MatchService matchService;
    private final RankingService rankingService;
    private final WinDecider winDecider;

    public InputScoreService(MatchService matchService, RankingService rankingService, WinDecider winDecider) {
        this.matchService = matchService;
        this.rankingService = rankingService;
        this.winDecider = winDecider;
    }

    public void inputScore(final Long matchId, final String score) {
        final Match match = matchService.getById(matchId);

        matchService.updateMatchScore(match, score);

        rankingService.updateWinner(winDecider.decideWinner(match));
        rankingService.updateLoser(winDecider.decideLoser(match));

        matchService.updateMatchStatus(match, MatchStatus.SCORE_TO_BE_CONFIRMED);
    }
}
