package com.gruzini.tennistico.services.notifications;

import com.gruzini.tennistico.domain.*;
import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.exceptions.NoGuestInMatchException;
import com.gruzini.tennistico.exceptions.NotificationNotFoundException;
import com.gruzini.tennistico.mappers.MatchInfoParser;
import com.gruzini.tennistico.mappers.NotificationMapper;
import com.gruzini.tennistico.models.dto.NotificationDto;
import com.gruzini.tennistico.repositories.NotificationRepository;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final UserService userService;
    private final MatchService matchService;
    private final MatchInfoParser matchInfoParser;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper, UserService userService, MatchService matchService, MatchInfoParser matchInfoParser) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.userService = userService;
        this.matchService = matchService;
        this.matchInfoParser = matchInfoParser;
    }

    public Notification createNotificationForHost(final String senderUsername, final Long matchId, final NotificationType type) {
        final User sender = userService.getByEmail(senderUsername);
        final User recipient = getHost(matchId);
        return createNotification(sender, recipient, matchId, type);
    }

    private User getHost(final Long matchId) {
        final Match match = matchService.getById(matchId);
        final Player host = match.getHost();
        return userService.getByPlayer(host);
    }

    private Notification createNotification(final User sender, final User recipient, final Long matchId, final NotificationType notificationType) {
        final Notification notification = Notification.builder()
                .notificationType(notificationType)
                .createdAt(LocalDateTime.now())
                .sender(sender)
                .recipient(recipient)
                .matchId(matchId)
                .clicked(false)
                .matchInfo(new MatchInfo(matchInfoParser.getOpponentName(matchId,recipient.getPlayer()), matchInfoParser.getScore(matchId, recipient.getPlayer())))
                .build();
        return notificationRepository.save(notification);
    }

    public Notification createNotificationForGuest(final String senderUsername, final Long matchId, final NotificationType type) {
        final User sender = userService.getByEmail(senderUsername);
        final User recipient = getGuest(matchId);
        return createNotification(sender, recipient, matchId, type);
    }

    private User getGuest(final Long matchId) {
        final Match match = matchService.getById(matchId);
        final Player guest = match.getGuest().orElseThrow(NoGuestInMatchException::new);
        return userService.getByPlayer(guest);
    }

    public Notification createNotification(final String recipientName, final NotificationType notificationType) {
        final User recipient = userService.getByEmail(recipientName);
        return createNotification(null, recipient, null, notificationType);
    }

    public List<NotificationDto> getByUser(String username) {
        return notificationRepository.findAllByRecipient(username).stream()
                .map(notificationMapper::toNotificationDto)
                .collect(Collectors.toList());
    }

    public void markAsClicked(Long triggerNotificationId) {
        final Notification notification = notificationRepository.findById(triggerNotificationId).orElseThrow(NotificationNotFoundException::new);
        notification.setClicked(true);
        notificationRepository.save(notification);
    }

    public List<Notification> getByMatchId(final Long matchId) {
        return notificationRepository.findAllByMatchId(matchId);
    }
}
