package com.gruzini.tennistico.services.point_counter;

import com.gruzini.tennistico.domain.Player;

public interface RankingPointCounter {
    int calculateWinPoints(Integer winnerRanking, Integer loserRanking);
    int calculateDrawPoints(Integer player1Ranking, Integer player2Ranking);
    int calculateLossPoints(Integer loserRanking, Integer winnerRanking);
}
