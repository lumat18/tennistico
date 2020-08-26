package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.events.ConfirmJoinEvent;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.PlayerService;
import com.gruzini.tennistico.validators.MatchAndPlayerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConfirmJoinService {

    private final PlayerService playerService;
    private final MatchService matchService;
    private final MatchAndPlayerValidator validator;

    public ConfirmJoinService(PlayerService playerService, MatchService matchService, MatchAndPlayerValidator validator) {
        this.playerService = playerService;
        this.matchService = matchService;
        this.validator = validator;
    }

    @EventListener
    public void handleConfirmJoinEvent(final ConfirmJoinEvent event) {
        confirmJoin(event.getMatchId(), event.getUsername());
    }

    private synchronized void confirmJoin(final Long matchId, final String username) {
        final Player player = playerService.getByUsername(username);
        final Match match = matchService.getById(matchId);
        validateMatchAndPlayer(match, player);
        matchService.updateMatchStatus(match, MatchStatus.UPCOMING);
    }

    private void validateMatchAndPlayer(final Match match, final Player player) {
        validator.isMatchStatusCorrect(match.getMatchStatus(), MatchStatus.JOIN_REQUEST);
        validator.bothPlayersExist(match);
        validator.isPlayerHost(match, player);
    }
}
