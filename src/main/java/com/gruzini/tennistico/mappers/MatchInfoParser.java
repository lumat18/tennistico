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
    public String getScoreAsLink(final Long matchId, final Player player){
        final String score = getScore(matchId, player);
        return "<span class='player-modal' th:attr='data-id=${notification.getMatchId}' onclick='showPlayerModal(this.getAttribute(&#39data-id&#39))'>" + score + "</span>";
//        return "<span class='player-modal' onclick='showModal(${matchId})'>" + score + "</span>";
    }

    private String getDivertedScoreString(final Match match, final Player player) {
        StringBuilder stringBuilder = new StringBuilder();
        final Score score = scoreService.getScoreWithSets(match.getScore().getId());
        score.getSets().forEach(set -> {
            if (player.equals(match.getHost())) {
                stringBuilder.append(appendSet(set.getHostScore(), set.getGuestScore()));
            } else {
                stringBuilder.append(appendSet(set.getGuestScore(), set.getHostScore()));
            }
        });
        return stringBuilder.toString().trim();
    }
    private String appendSet(int recipientScore, int opponentScore) {
        return recipientScore + "-" + opponentScore + " ";
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

    public String getOpponentNameAsLink(final Long matchId, final Player player) {
        final String opponentName = getOpponentName(matchId, player);
        return "<span class='player-modal' th:attr='data-id=${notification.getMatchId}' onclick='showPlayerModal(this.getAttribute(&#39data-id&#39))'>" + opponentName + "</span>";

    }
}
