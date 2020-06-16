package com.gruzini.tennistico.models.score;

import org.springframework.stereotype.Component;

@Component(value = "simple")
public class SimpleRankingPointCounter implements RankingPointCounter {
    @Override
    public int calculateWinPoints() {
        return 10;
    }

    @Override
    public int calculateLossPoints() {
        return 1;
    }
}
