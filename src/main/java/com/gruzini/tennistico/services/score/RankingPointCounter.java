package com.gruzini.tennistico.services.score;

public interface RankingPointCounter {
    int calculateWinPoints();

    int calculateLossPoints();
}
