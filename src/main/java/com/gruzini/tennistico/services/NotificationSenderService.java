package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
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
    private final MatchService matchService;
    private final UserService userService;

    public NotificationSenderService(NotificationService notificationService, MatchService matchService, UserService userService) {
        this.notificationService = notificationService;
        this.matchService = matchService;
        this.userService = userService;
    }

    @EventListener
    public void handleEvent(final CreateMatchEvent event) {
        final User user = userService.getByEmail(event.getUsername());
        final Notification notification = notificationService.createNotification(user, NotificationType.MATCH_CREATED);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + user.getEmail() + " created");
    }

    @EventListener
    public void handleEvent(final JoinMatchEvent event) {
        final User sender = userService.getByEmail(event.getUsername());
        final Match match = matchService.getById(event.getMatchId());
        final Player host = match.getHost();
        final User recipient = userService.getByPlayer(host);
        final Notification notification = notificationService.createNotification(sender, recipient, event.getMatchId(), NotificationType.JOIN_REQUEST);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + recipient.getEmail() + " created");
    }

    @EventListener
    public void handleEvent(final ConfirmJoinEvent event) {
        final User sender = userService.getByEmail(event.getUsername());
        final Match match = matchService.getById(event.getMatchId());
        final Player guest = match.getGuest();
        final User recipient = userService.getByPlayer(guest);
        final Notification notification = notificationService.createNotification(sender, recipient, event.getMatchId(), NotificationType.JOIN_CONFIRMED);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + recipient.getEmail() + " created");
    }

    @EventListener
    public void handleEvent(final InputScoreEvent event) {
        final User sender = userService.getByEmail(event.getUsername());
        final Match match = matchService.getById(event.getMatchId());
        final Player guest = match.getGuest();
        final User recipient = userService.getByPlayer(guest);
        final Notification notification = notificationService.createNotification(sender, recipient, match.getId(), NotificationType.SCORE_TO_CONFIRM);
        log.info("Notification of type " + notification.getNotificationType().toString() + " for user " + recipient.getEmail() + " created");
    }

    @EventListener
    public void handleEvent(final ConfirmScoreEvent event) {
        final User guest = userService.getByEmail(event.getUsername());
        final Match match = matchService.getById(event.getMatchId());
        final Player hostPlayer = match.getHost();
        final User host = userService.getByPlayer(hostPlayer);
        final Notification notificationForHost = notificationService.createNotification(guest, NotificationType.MATCH_ARCHIVED);
        final Notification notificationForGuest = notificationService.createNotification(host, NotificationType.MATCH_ARCHIVED);
        log.info("Notification of type " + notificationForHost.getNotificationType().toString() + " for user " + host.getEmail() + " created");
        log.info("Notification of type " + notificationForGuest.getNotificationType().toString() + " for user " + guest.getEmail() + " created");
    }
}
