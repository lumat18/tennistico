package com.gruzini.tennistico.services;

import org.springframework.stereotype.Component;

@Component
public class WinDecidingService {
    private static final int HOST_INDEX = 0;
    private static final int GUEST_INDEX = 1;
    private static final String SEPARATOR = "-";

//    public Player decideWinner(final Match match) {
//        if (isDraw(match)) {
//            return null;
//        }
//        if (hostWins(match)) {
//            return match.getHost();
//        } else {
//            return match.getGuest().orElseThrow(MatchPlayersException::new);
//        }
//    }

//    private boolean isDraw(final Match match) {
//        final String score = match.getScore();
//        final int hostScore = getPlayerScore(score, HOST_INDEX);
//        final int guestScore = getPlayerScore(score, GUEST_INDEX);
//        return hostScore == guestScore;
//    }
//
//    private boolean hostWins(final Match match) {
//        final String score = match.getScore();
//        final int hostScore = getPlayerScore(score, HOST_INDEX);
//        final int guestScore = getPlayerScore(score, GUEST_INDEX);
//        return hostScore > guestScore;
//    }

//    private int getPlayerScore(final String score, final int playerIndex) {
//        final String[] split = score.split(SEPARATOR);
//        return Integer.parseInt(split[playerIndex]);
//    }
//
//    public Player decideLoser(final Match match) {
//        if (isDraw(match)) {
//            return null;
//        }
//        if (hostWins(match)) {
//            return match.getGuest().orElseThrow(MatchPlayersException::new);
//        } else {
//            return match.getHost();
//        }
//    }
}
