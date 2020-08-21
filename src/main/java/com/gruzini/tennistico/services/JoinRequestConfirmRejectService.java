package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.events.ConfirmJoinEvent;
import com.gruzini.tennistico.events.RejectJoinEvent;
import com.gruzini.tennistico.exceptions.MatchPlayersException;
import com.gruzini.tennistico.exceptions.PlayerIsNotAMatchHostException;
import com.gruzini.tennistico.exceptions.WrongMatchStatusException;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class JoinRequestConfirmRejectService {

    private final PlayerService playerService;
    private final MatchService matchService;

    public JoinRequestConfirmRejectService(PlayerService playerService, MatchService matchService) {
        this.playerService = playerService;
        this.matchService = matchService;
    }

    @EventListener
    public void handleConfirmJoinEvent(final ConfirmJoinEvent event) {
        confirmJoin(event.getMatchId(), event.getUsername());
    }

    private synchronized void confirmJoin(final Long matchId, final String username) {
        processStatusUpdate(matchId, username, MatchStatus.UPCOMING);
        System.out.println("confirm join method called");
    }

    private void processStatusUpdate(final Long matchId, final String username, final MatchStatus desiredStatus) {
        final Player player = playerService.getByUsername(username);
        final Match match = matchService.getById(matchId);
        validateMatchAndPlayer(match, player);
        matchService.updateMatchStatus(match, desiredStatus);
    }

    private void validateMatchAndPlayer(final Match match, final Player player) {
        validateMatchStatus(match.getMatchStatus());
        validateMatchHost(match, player);
        validateMatchPlayers(match);
    }

    private void validateMatchPlayers(final Match match) {
        if (isNull(match.getHost()) || isNull(match.getGuest())) {
            throw new MatchPlayersException();
        }
    }

    private void validateMatchStatus(final MatchStatus status) {
        if (!status.equals(MatchStatus.JOIN_REQUEST)) {
            throw new WrongMatchStatusException();
        }
    }

    private void validateMatchHost(final Match match, final Player player) {
        if (!match.getHost().equals(player)) {
            throw new PlayerIsNotAMatchHostException();
        }
    }

    @EventListener
    public void handleRejectJoinEvent(final RejectJoinEvent event) {
        rejectJoin(event.getMatchId(), event.getUsername());
        System.out.println("reject join method called");
    }

    private synchronized void rejectJoin(final Long matchId, final String username) {
        processStatusUpdate(matchId, username, MatchStatus.HOSTED);
        removeGuest(matchId);
    }
    private void removeGuest(final Long matchId){
        final Match match = matchService.getById(matchId);
        match.removeGuest();
        matchService.save(match);
    }
}
