package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.events.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class NotificationSenderService {

    private final NotificationService notificationService;

    public NotificationSenderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @EventListener
    public void handleEvent(final CreateMatchEvent event) {
        final Notification notification = notificationService.createNotification(event.getUsername(), NotificationType.MATCH_CREATED);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + event.getUsername() + " created");
    }

    @EventListener
    public void handleEvent(final JoinMatchEvent event) {
        final Notification notification = notificationService.createNotificationForHost(event.getUsername(), event.getMatchId(), NotificationType.JOIN_REQUEST);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + notification.getRecipient() + " created");
    }

    @EventListener
    public void handleEvent(final ConfirmJoinEvent event) {
        final Notification notification = notificationService.createNotificationForGuest(event.getUsername(), event.getMatchId(), NotificationType.JOIN_CONFIRMED);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + notification.getRecipient() + " created");
    }

    @EventListener
    public void handleEvent(final InputScoreEvent event) {
        final Notification notification = notificationService.createNotificationForGuest(event.getUsername(), event.getMatchId(), NotificationType.SCORE_TO_CONFIRM);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + notification.getRecipient() + " created");
    }

    @EventListener
    public void handleEvent(final ConfirmScoreEvent event) {
        final Notification notificationForHost = notificationService.createNotificationForHost(event.getUsername(), event.getMatchId(), NotificationType.MATCH_ARCHIVED);
        log.info("Notification of type " + notificationForHost.getNotificationType().toString() + " for user " + notificationForHost.getRecipient() + " created");

        final Notification notificationForGuest = notificationService.createNotificationForGuest(event.getUsername(), event.getMatchId(), NotificationType.MATCH_ARCHIVED);
        log.info("Notification of type " + notificationForGuest.getNotificationType().toString() + " for user " + notificationForGuest.getRecipient() + " created");
    }
}
