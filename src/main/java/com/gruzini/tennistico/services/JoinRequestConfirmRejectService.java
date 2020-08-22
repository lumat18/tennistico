package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.events.ConfirmJoinEvent;
import com.gruzini.tennistico.events.RejectJoinEvent;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.PlayerService;
import com.gruzini.tennistico.validators.MatchAndPlayerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JoinRequestConfirmRejectService {

    private final PlayerService playerService;
    private final MatchService matchService;
    private final MatchAndPlayerValidator validator;

    public JoinRequestConfirmRejectService(PlayerService playerService, MatchService matchService, MatchAndPlayerValidator validator) {
        this.playerService = playerService;
        this.matchService = matchService;
        this.validator = validator;
    }

    @EventListener
    public void handleConfirmJoinEvent(final ConfirmJoinEvent event) {
        confirmJoin(event.getMatchId(), event.getUsername());
    }

    private synchronized void confirmJoin(final Long matchId, final String username) {
        processStatusUpdate(matchId, username, MatchStatus.UPCOMING);
    }

    private void processStatusUpdate(final Long matchId, final String username, final MatchStatus desiredStatus) {
        final Player player = playerService.getByUsername(username);
        final Match match = matchService.getById(matchId);
        validateMatchAndPlayer(match, player);
        matchService.updateMatchStatus(match, desiredStatus);
    }

    private void validateMatchAndPlayer(final Match match, final Player player) {
        validator.isMatchStatusCorrect(match.getMatchStatus(), MatchStatus.JOIN_REQUEST);
        validator.bothPlayersExist(match);
        validator.isPlayerHost(match, player);
    }

    @EventListener
    @Order(1) // THIS HAS TO OCCUR AFTER NOTIFICATION BEING SENT
    public void handleRejectJoinEvent(final RejectJoinEvent event) {
        rejectJoin(event.getMatchId(), event.getUsername());
    }

    private synchronized void rejectJoin(final Long matchId, final String username) {
        processStatusUpdate(matchId, username, MatchStatus.HOSTED);
        removeGuest(matchId);
    }

    private void removeGuest(final Long matchId) {
        final Match match = matchService.getById(matchId);
        match.removeGuest();
        matchService.save(match);
    }
}
