package com.gruzini.tennistico.services.notifications;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Notification;
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
public class NotificationCreateListener {

    private final NotificationService notificationService;
    private final MatchService matchService;
    private final UserService userService;

    public NotificationCreateListener(NotificationService notificationService, MatchService matchService, UserService userService) {
        this.notificationService = notificationService;
        this.matchService = matchService;
        this.userService = userService;
    }

    /* THIS EVENT IS ISSUED BY CREATE MATCH CONTROLLER WHEN USER CREATES NEW MATCH */
    @EventListener
    public void handleEvent(final CreateMatchEvent event) {
        final Notification notification = notificationService.createNotification(event.getUsername(), NotificationType.MATCH_CREATED);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + event.getUsername() + " created");
    }

    /* THIS EVENT IS ISSUED BY JOIN MATCH CONTROLLER WHEN USER CLICKS BUTTON ON OPEN MATCHES LIST */
    @EventListener
    public void handleEvent(final JoinMatchEvent event) {
        final Notification notification = notificationService.createNotificationForHost(event.getUsername(), event.getMatchId(), NotificationType.JOIN_REQUEST);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + notification.getRecipient().getEmail() + " created");
    }

    /* THIS EVENT IS ISSUED BY CONFIRM JOIN CONTROLLER WHEN USER CLICKS BUTTON ON NOTIFICATION */
    @EventListener
    public void handleEvent(final ConfirmJoinEvent event) {
        final Notification notification = notificationService.createNotificationForGuest(event.getUsername(), event.getMatchId(), NotificationType.JOIN_CONFIRMED);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + notification.getRecipient().getEmail() + " created");
    }

    /* THIS EVENT IS ISSUED BY REJECT JOIN CONTROLLER WHEN USER CLICKS BUTTON ON NOTIFICATION */
    @EventListener
    @Order(0)
    public void handleEvent(final RejectJoinEvent event) {
        final Notification notification = notificationService.createNotificationForGuest(event.getUsername(), event.getMatchId(), NotificationType.JOIN_REQUEST_REJECTED);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + notification.getRecipient().getEmail() + " created");
    }

    /* THIS EVENT IS ISSUED BY THE SCHEDULED PUBLISHER */
    @EventListener
    @Order(0)
    public void handleEvent(final HeldMatchEvent event) {
        final List<Match> matches = matchService.getAllExpiredByStatus(event.getCurrentStatus());
        matches.forEach(match -> {
            final Notification notification = notificationService.createNotificationForHost(userService.getByPlayer(match.getHost()).getEmail(),
                    match.getId(),
                    NotificationType.SCORE_TO_SUBMIT);
            log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + notification.getRecipient().getEmail() + " created");
        });
    }

    /* THIS EVENT IS ISSUED BY INPUT SCORE CONTROLLER WHEN USER SUBMITS SCORE */
    @EventListener
    public void handleEvent(final InputScoreEvent event) {
        final Notification notification = notificationService.createNotificationForGuest(event.getUsername(), event.getMatchId(), NotificationType.SCORE_TO_CONFIRM);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + notification.getRecipient().getEmail() + " created");
    }

    /* THIS EVENT IS ISSUED BY CONFIRM SCORE CONTROLLER WHEN USER CLICKS BUTTON ON NOTIFICATION */
    @EventListener
    public void handleEvent(final ConfirmScoreEvent event) {
        final Notification notificationForHost = notificationService.createNotificationForHost(event.getUsername(), event.getMatchId(), NotificationType.MATCH_ARCHIVED);
        log.info("Notification of type " + notificationForHost.getNotificationType().toString() + " for user " + notificationForHost.getRecipient().getEmail() + " created");

        final Notification notificationForGuest = notificationService.createNotificationForGuest(event.getUsername(), event.getMatchId(), NotificationType.MATCH_ARCHIVED);
        log.info("Notification of type " + notificationForGuest.getNotificationType().toString() + " for user " + notificationForGuest.getRecipient().getEmail() + " created");
    }

    /* THIS EVENT IS ISSUED BY REJECT SCORE CONTROLLER WHEN USER CLICKS BUTTON ON NOTIFICATION */
    @EventListener
    public void handleEvent(final RejectScoreEvent event) {
        final Notification notification = notificationService.createNotificationForHost(event.getUsername(), event.getMatchId(), NotificationType.SCORE_REJECTED);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + notification.getRecipient().getEmail() + " created");
    }
}
