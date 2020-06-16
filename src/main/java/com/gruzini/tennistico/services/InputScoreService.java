package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.score.WinDecider;
import org.springframework.stereotype.Service;

@Service
public class InputScoreService {

    private final RankingService rankingService;
    private final WinDecider winDecider;

    public InputScoreService(RankingService rankingService, WinDecider winDecider) {
        this.rankingService = rankingService;
        this.winDecider = winDecider;
    }

    public void inputScore(final Match match) {
        final Player winner = winDecider.decideWinner(match);
        final Player loser = winDecider.decideLoser(match);

        rankingService.updateWinner(winner);
        rankingService.updateLoser(loser);
    }
}
