package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.events.CreateMatchEvent;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class NotificationSenderService {

    private final NotificationService notificationService;
    private final MatchService matchService;
    private final UserService userService;

    public NotificationSenderService(NotificationService notificationService, MatchService matchService, UserService userService) {
        this.notificationService = notificationService;
        this.matchService = matchService;
        this.userService = userService;
    }

    @EventListener
    public void handleSendNotificationEvent(final CreateMatchEvent event) {
        final User user = userService.getByEmail(event.getUsername());
        final Notification notification = notificationService.createNotification(user, NotificationType.MATCH_CREATED);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + user.getEmail() + " created");
    }

    public Notification sendToHost(final Long matchId, final NotificationType type) {
        final Player host = matchService.getById(matchId).getHost();
        return notificationService.createNotification(host, matchId, type);
    }

    public Notification sendToGuest(final Long matchId, final NotificationType type) {
        final Player guest = matchService.getById(matchId).getGuest().orElseThrow(PlayerNotFoundException::new);
        return notificationService.createNotification(guest, matchId, type);
    }
}
