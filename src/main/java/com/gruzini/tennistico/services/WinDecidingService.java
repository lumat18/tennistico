package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Score;
import com.gruzini.tennistico.domain.Set;
import com.gruzini.tennistico.exceptions.NoGuestInMatchException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WinDecidingService {
    public Score updateWinnerAndLoser(final Match match){
        int hostScore = 0;
        int guestScore = 0;
        final Score score = match.getScore();
        List<Set> setList = score.getSets();
        for (Set set : setList) {
            if (set.getHostScore() > set.getGuestScore()) {
                hostScore += 1;
            } else {
                guestScore += 1;
            }
        }
        if(hostScore >= guestScore){
            score.setWinner(match.getHost());
            score.setLoser(match.getGuest().orElseThrow(NoGuestInMatchException::new));
            score.setDraw(hostScore == guestScore);
        } else {
            score.setWinner(match.getGuest().orElseThrow(NoGuestInMatchException::new));
            score.setLoser(match.getHost());
            score.setDraw(false);
        }
        return score;
    }
}
