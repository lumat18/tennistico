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
    private final NotificationSenderService notificationSender;

    public ConfirmScoreService(final PlayerService playerService,
                               final MatchService matchService, NotificationSenderService notificationSender) {
        this.playerService = playerService;
        this.matchService = matchService;
        this.notificationSender = notificationSender;
    }

    public void confirmScore(final Long matchId, final String username) {
        final Player player = playerService.getByUsername(username);
        final Match match = matchService.getById(matchId);
        validateMatchAndPlayer(match, player);
        matchService.updateMatchStatus(match, MatchStatus.ARCHIVED);
        notificationSender.sendToGuest(matchId, NotificationType.SCORE_TO_SUBMIT);
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
}
