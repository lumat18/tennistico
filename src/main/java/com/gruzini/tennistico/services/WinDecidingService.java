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
        final Score score = match.getScore();
        final String result = calculateMatchResult(score);

        if (result.equals("guest")){
            score.setWinner(match.getGuest().orElseThrow(NoGuestInMatchException::new));
            score.setLoser(match.getHost());
            score.setDraw(false);
        } else {
            score.setWinner(match.getHost());
            score.setLoser(match.getGuest().orElseThrow(NoGuestInMatchException::new));
            score.setDraw(result.equals("draw"));
        }
        return score;
    }

    private String calculateMatchResult(final Score score){
        int hostScore = 0;
        int guestScore = 0;
        List<Set> setList = score.getSets();
        for (Set set : setList) {
            if (set.getHostScore() > set.getGuestScore()) {
                hostScore += 1;
            } else {
                guestScore += 1;
            }
        }
        
        if(hostScore > guestScore){
            return "host";
        } else if (hostScore < guestScore){
            return "guest";
        } else {
            return "draw";
        }
    }
}
