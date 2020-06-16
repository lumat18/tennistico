package com.gruzini.tennistico.models.score;

public interface RankingPointCounter {
    int calculateWinPoints();

    int calculateLossPoints();
}
