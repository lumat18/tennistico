package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.exceptions.MatchPlayersException;
import com.gruzini.tennistico.exceptions.PlayerIsNotAMatchHostException;
import com.gruzini.tennistico.exceptions.WrongMatchStatusException;
import org.springframework.stereotype.Service;

@Service
public class ConfirmJoinService {

    private final PlayerService playerService;
    private final MatchService matchService;
    private final NotificationService notificationService;
    private final UserService userService;

    public ConfirmJoinService(PlayerService playerService, MatchService matchService, NotificationService notificationService, UserService userService) {
        this.playerService = playerService;
        this.matchService = matchService;
        this.notificationService = notificationService;
        this.userService = userService;
    }

    public void confirmJoin(final Long matchId, final String username) {
        final Player player = playerService.getByUsername(username);
        final Match match = matchService.getById(matchId);
        validateMatchAndPlayer(match, player);
        matchService.updateMatchStatus(match, MatchStatus.UPCOMING);
        sendNotificationToMatchGuest(matchId);
    }

    private void validateMatchAndPlayer(final Match match, final Player player) {
        validateMatchStatus(match);
        validateMatchHost(match, player);
        validateMatchPlayers(match);
    }

    private void validateMatchPlayers(final Match match) {
        if (match.getPlayers().size() != 2) {
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

    private void sendNotificationToMatchGuest(Long matchId) {
        notificationService.createNotificationFor(userService.getByPlayer(matchService.getById(matchId).getGuest().get()), NotificationType.JOIN_CONFIRMED);
    }
}
