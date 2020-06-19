package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.events.ConfirmScoreEvent;
import com.gruzini.tennistico.exceptions.PlayerIsNotMatchGuestException;
import com.gruzini.tennistico.exceptions.WrongMatchStatusException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ConfirmScoreService {
    private final PlayerService playerService;
    private final MatchService matchService;

    public ConfirmScoreService(PlayerService playerService, MatchService matchService) {
        this.playerService = playerService;
        this.matchService = matchService;
    }

    @EventListener
    public void handleConfirmScoreEvent(final ConfirmScoreEvent event) {
        confirmScore(event.getMatchId(), event.getUsername());
    }

    public void confirmScore(final Long matchId, final String username) {
        final Player player = playerService.getByUsername(username);
        final Match match = matchService.getById(matchId);
        validateMatchAndPlayer(match, player);
        matchService.updateMatchStatus(match, MatchStatus.ARCHIVED);
    }

    private void validateMatchAndPlayer(final Match match, final Player player) {
        validateMatchStatus(match);
        validateMatchGuest(match, player);
    }

    private void validateMatchStatus(final Match match) {
        if (!match.getMatchStatus().equals(MatchStatus.SCORE_TO_BE_CONFIRMED)) {
            throw new WrongMatchStatusException();
        }
    }

    private void validateMatchGuest(final Match match, final Player player) {
        final Player guest = match.getGuest();
        if (!guest.equals(player)) {
            throw new PlayerIsNotMatchGuestException();
        }
    }
}
