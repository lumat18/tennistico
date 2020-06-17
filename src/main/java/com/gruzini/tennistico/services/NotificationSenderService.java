package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificationSenderService {

    private final NotificationService notificationService;
    private final MatchService matchService;

    public NotificationSenderService(NotificationService notificationService, MatchService matchService) {
        this.notificationService = notificationService;
        this.matchService = matchService;
    }

    public Notification sendToHost(final Long matchId, final NotificationType type) {
        final Player host = matchService.getById(matchId).getHost();
        return notificationService.createNotificationFor(host, matchId, type);
    }
    public Notification sendToGuest(final Long matchId, final NotificationType type) {
        final Player guest = matchService.getById(matchId).getGuest().orElseThrow(PlayerNotFoundException::new);
        return notificationService.createNotificationFor(guest, matchId, type);
    }
}
