package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.events.RejectScoreEvent;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.PlayerService;
import com.gruzini.tennistico.validators.MatchAndPlayerValidator;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class RejectScoreService {
    private final PlayerService playerService;
    private final MatchService matchService;
    private final MatchAndPlayerValidator validator;

    public RejectScoreService(PlayerService playerService, MatchService matchService, MatchAndPlayerValidator validator) {
        this.playerService = playerService;
        this.matchService = matchService;
        this.validator = validator;
    }

    @EventListener
    public void handleRejectScoreEvent(final RejectScoreEvent event) {
        rejectScore(event.getMatchId(), event.getUsername());
    }

    private void rejectScore(final Long matchId, final String username) {
        processStatusUpdate(matchId, username, MatchStatus.PENDING);
    }

    private void processStatusUpdate(final Long matchId, final String username, final MatchStatus desiredStatus) {
        final Player player = playerService.getByUsername(username);
        final Match match = matchService.getById(matchId);
        validateMatchAndPlayer(match, player);
        matchService.updateMatchStatus(match, desiredStatus);
    }

    private void validateMatchAndPlayer(final Match match, final Player player) {
        validator.isMatchStatusCorrect(match.getMatchStatus(), MatchStatus.SCORE_TO_BE_CONFIRMED);
        validator.isPlayerGuest(match, player);
    }
}

