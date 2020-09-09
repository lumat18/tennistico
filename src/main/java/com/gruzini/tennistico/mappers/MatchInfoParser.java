package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.Score;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.ScoreService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class MatchInfoParser {
    private final MatchService matchService;
    private final ScoreService scoreService;

    public MatchInfoParser(MatchService matchService, ScoreService scoreService) {
        this.matchService = matchService;
        this.scoreService = scoreService;
    }

    public String getScore(final Long matchId, final Player player) {
        if (isNull(matchId)) {
            return "";
        }
        final Match match = matchService.getById(matchId);
        if (isNull(match.getScore())) {
            return "";
        }
        return getDivertedScoreString(match, player);
    }

    private String getDivertedScoreString(final Match match, final Player player) {
        final Score score = scoreService.getScoreWithSets(match.getScore().getId());
        return score.getSets()
                .stream().map(set -> {
                    if (player.equals(match.getHost())) {
                        return appendSet(set.getHostScore(), set.getGuestScore());
                    } else {
                        return appendSet(set.getGuestScore(), set.getHostScore());
                    }
                })
                .collect(Collectors.joining(" "));
    }

    private String appendSet(int recipientScore, int opponentScore) {
        return recipientScore + "-" + opponentScore;
    }

    public Player getOpponent(final Match match, final Player player) {
        Player opponent;
        if (match.getHost().equals(player)) {
            opponent = match.getGuest().orElse(null);
        } else {
            opponent = match.getHost();
        }
        return opponent;
    }

    public String getOpponentName(final Long matchId, final Player player) {
        if (isNull(matchId)) {
            return "";
        }
        final Match match = matchService.getById(matchId);
        Player opponent = getOpponent(match, player);
        if (isNull(opponent)) {
            return null;
        }
        return opponent.getFullName();
    }

    public Long getOpponentId(final Long matchId, final Player player) {
        if (isNull(matchId)) {
            return null;
        }
        final Match match = matchService.getById(matchId);
        Player opponent = getOpponent(match, player);
        if (isNull(opponent)) {
            return null;
        }
        return opponent.getId();
    }

    public String getMatchResult(final Match match, final Player player) {
        if (match.getScore().isDraw()) {
            return "DRAW";
        } else {
            if (match.getScore().getWinner().equals(player)) {
                return "WIN";
            } else {
                return "LOSS";
            }
        }
    }
}
