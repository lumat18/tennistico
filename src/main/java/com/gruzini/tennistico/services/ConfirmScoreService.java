package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.exceptions.NoGuestInMatchException;
import com.gruzini.tennistico.exceptions.PlayerIsNotMatchGuestException;
import com.gruzini.tennistico.exceptions.WrongMatchStatusException;
import org.springframework.stereotype.Service;

@Service
public class ConfirmScoreService {
    private final PlayerService playerService;
    private final MatchService matchService;
    private final NotificationService notificationService;
    private final UserService userService;

    public ConfirmScoreService(final PlayerService playerService,
                               final MatchService matchService, NotificationService notificationService, UserService userService) {
        this.playerService = playerService;
        this.matchService = matchService;
        this.notificationService = notificationService;
        this.userService = userService;
    }

    public void confirmScore(final Long matchId, final String username) {
        final Player player = playerService.getByUsername(username);
        final Match match = matchService.getById(matchId);
        validateMatchAndPlayer(match, player);
        matchService.updateMatchStatus(match, MatchStatus.ARCHIVED);
        sendNotificationToMatchHost(matchId);
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
        final Player guest = match.getGuest().orElseThrow(NoGuestInMatchException::new);
        if (!guest.equals(player)) {
            throw new PlayerIsNotMatchGuestException();
        }
    }

    private void sendNotificationToMatchHost(Long matchId) {
        notificationService.createNotificationFor(userService.getByPlayer(matchService.getById(matchId).getHost()), NotificationType.SCORE_TO_CONFIRM);
    }
}
