package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.events.*;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class NotificationEventListener {

    private final NotificationService notificationService;
    private final MatchService matchService;
    private final UserService userService;

    public NotificationEventListener(NotificationService notificationService, MatchService matchService, UserService userService) {
        this.notificationService = notificationService;
        this.matchService = matchService;
        this.userService = userService;
    }

    @EventListener
    public void handleEvent(final CreateMatchEvent event) {
        final Notification notification = notificationService.createNotification(event.getUsername(), NotificationType.MATCH_CREATED);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + event.getUsername()  + " created");
    }

    @EventListener
    public void handleEvent(final JoinMatchEvent event) {
        final Notification notification = notificationService.createNotificationForHost(event.getUsername(), event.getMatchId(), NotificationType.JOIN_REQUEST);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + notification.getRecipient().getEmail()  + " created");
    }

    @EventListener
    public void handleEvent(final ConfirmJoinEvent event) {
        final Notification notification = notificationService.createNotificationForGuest(event.getUsername(), event.getMatchId(), NotificationType.JOIN_CONFIRMED);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + notification.getRecipient().getEmail()  + " created");
    }

    @EventListener
    public void handleEvent(final InputScoreEvent event) {
        final Notification notification = notificationService.createNotificationForGuest(event.getUsername(), event.getMatchId(), NotificationType.SCORE_TO_CONFIRM);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + notification.getRecipient().getEmail()  + " created");
    }

    @EventListener
    public void handleEvent(final ConfirmScoreEvent event) {
        final Notification notificationForHost = notificationService.createNotificationForHost(event.getUsername(), event.getMatchId(), NotificationType.MATCH_ARCHIVED);
        log.info("Notification of type " + notificationForHost.getNotificationType().toString() + " for user " + notificationForHost.getRecipient().getEmail()  + " created");

        final Notification notificationForGuest = notificationService.createNotificationForGuest(event.getUsername(), event.getMatchId(), NotificationType.MATCH_ARCHIVED);
        log.info("Notification of type " + notificationForGuest.getNotificationType().toString() + " for user " + notificationForGuest.getRecipient().getEmail()  + " created");
    }

    @EventListener
    @Order(0)
    public void handleEvent(final HeldMatchEvent event) {
        final List<Player> hosts = matchService.getAllHostsByMatchStatus(event.getCurrentStatus());
        hosts.forEach(host -> {
            final Notification notification = notificationService.createNotification(userService.getByPlayer(host).getEmail(), NotificationType.SCORE_TO_SUBMIT);
            log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + notification.getRecipient().getEmail() + " created");
        });
    }
}
