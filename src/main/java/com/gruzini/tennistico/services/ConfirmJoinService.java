package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.exceptions.MatchPlayersException;
import com.gruzini.tennistico.exceptions.PlayerIsNotAMatchHostException;
import com.gruzini.tennistico.exceptions.WrongMatchStatusException;
import com.gruzini.tennistico.services.entities.MatchService;
import com.gruzini.tennistico.services.entities.PlayerService;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class ConfirmJoinService {

    private final PlayerService playerService;
    private final MatchService matchService;

    public ConfirmJoinService(PlayerService playerService, MatchService matchService) {
        this.playerService = playerService;
        this.matchService = matchService;
    }

    public void confirmJoin(final Long matchId, final String username) {
        final Player player = playerService.getByUsername(username);
        final Match match = matchService.getById(matchId);
        validateMatchAndPlayer(match, player);
        matchService.updateMatchStatus(match, MatchStatus.UPCOMING);
    }

    private void validateMatchAndPlayer(final Match match, final Player player) {
        validateMatchStatus(match);
        validateMatchHost(match, player);
        validateMatchPlayers(match);
    }

    private void validateMatchPlayers(final Match match) {
        if (isNull(match.getHost()) || isNull(match.getGuest())) {
            throw new MatchPlayersException();
        }
    }

    private void validateMatchStatus(final Match match) {
        if (!match.getMatchStatus().equals(MatchStatus.JOIN_REQUEST)) {
            throw new WrongMatchStatusException();
        }
    }

    private void validateMatchHost(final Match match, final Player player) {
        if (!match.getHost().equals(player)) {
            throw new PlayerIsNotAMatchHostException();
        }
    }
}
