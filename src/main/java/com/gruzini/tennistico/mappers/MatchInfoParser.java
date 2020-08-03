package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.Score;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.ScoreService;
import org.springframework.stereotype.Component;

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
        if (isNull(matchId)){
            return "";
        }
        final Match match = matchService.getById(matchId);
        if (isNull(match.getScore())) {
            return "";
        }
        return getDivertedScoreString(match, player);
    }

    private String getDivertedScoreString(final Match match, final Player player) {
        StringBuilder stringBuilder = new StringBuilder();
        final Score score = scoreService.getScoreWithSets(match.getScore().getId());
        score.getSets().forEach(set -> {
            if (player.equals(match.getHost())) {
                appendSet(stringBuilder, set.getHostScore(), set.getGuestScore());
            } else {
                appendSet(stringBuilder, set.getGuestScore(), set.getHostScore());
            }
        });
        return stringBuilder.toString();
    }

    private void appendSet(StringBuilder stringBuilder, int recipientScore, int opponentScore) {
        stringBuilder.append(recipientScore).append(" - ").append(opponentScore).append("  ");
    }

    public Player getOpponent(final Match match, final Player player) {
        Player opponent;
        if (match.getHost().equals(player)) {
            opponent = match.getGuest().orElseThrow(PlayerNotFoundException::new);
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
        return opponent.getFirstName() + " " + opponent.getLastName();
    }

}
