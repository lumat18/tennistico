package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.events.ConfirmJoinEvent;
import com.gruzini.tennistico.events.CreateMatchEvent;
import com.gruzini.tennistico.events.JoinMatchEvent;
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

    @EventListener
    public void handleSendNotificationEvent(final JoinMatchEvent event) {
        final User sender = userService.getByEmail(event.getUsername());
        final Match match = matchService.getById(event.getMatchId());
        final Player host = match.getHost();
        final User recipient = userService.getByPlayer(host);
        final Notification notification = notificationService.createNotification(sender, recipient, event.getMatchId(), NotificationType.JOIN_REQUEST);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + recipient.getEmail() + " created");
    }

    @EventListener
    public void handleSendNotificationEvent(final ConfirmJoinEvent event) {
        final User sender = userService.getByEmail(event.getUsername());
        final Match match = matchService.getById(event.getMatchId());
        final Player guest = match.getGuest();
        final User recipient = userService.getByPlayer(guest);
        final Notification notification = notificationService.createNotification(sender, recipient, event.getMatchId(), NotificationType.JOIN_CONFIRMED);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + recipient.getEmail() + " created");
    }

    public Notification sendToHost(final Long matchId, final NotificationType type) {
        final Player host = matchService.getById(matchId).getHost();
        return notificationService.createNotification(host, matchId, type);
    }

    public Notification sendToGuest(final Long matchId, final NotificationType type) {
        final Player guest = matchService.getById(matchId).getGuest();
        return notificationService.createNotification(guest, matchId, type);
    }
}
