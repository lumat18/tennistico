package com.gruzini.tennistico.services.point_counter;

import org.springframework.stereotype.Component;

@Component(value = "simple")
public class SimpleRankingPointCounter implements RankingPointCounter {
    @Override
    public int calculateWinPoints(Integer winnerRanking, Integer loserRanking) {
        return winnerRanking + 10;
    }

    @Override
    public int calculateDrawPoints(Integer player1Ranking, Integer player2Ranking) {
        return player1Ranking + 5;
    }

    @Override
    public int calculateLossPoints(Integer loserRanking, Integer winnerRanking) {
        return loserRanking + 1;
    }
}
