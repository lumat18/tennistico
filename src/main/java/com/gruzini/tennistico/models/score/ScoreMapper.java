package com.gruzini.tennistico.models.score;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.nonNull;

@Component
public class ScoreMapper {

    public String mapScoreToString(final Score score) {
        final List<Set> sets = score.getSets();
        int hostScore = 0;
        int guestScore = 0;
        for (Set set : sets) {
            if (nonNull(set)) {
                if (set.getHostScore() > set.getGuestScore()) {
                    hostScore++;
                } else {
                    guestScore++;
                }
            }
        }
        return hostScore + "-" + guestScore;
    }
}
