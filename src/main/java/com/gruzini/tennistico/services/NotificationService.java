package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.mappers.NotificationMapper;
import com.gruzini.tennistico.models.dto.NotificationDto;
import com.gruzini.tennistico.repositories.NotificationRepository;
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

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper, UserService userService, MatchService matchService) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.userService = userService;
        this.matchService = matchService;
    }

    public Notification createNotificationForHost(final String senderUsername, final Long matchId, final NotificationType type) {
        final User sender = userService.getByEmail(senderUsername);
        final Match match = matchService.getById(matchId);
        final Player host = match.getHost();
        final User recipient = userService.getByPlayer(host);
        return createNotification(sender, recipient, matchId, type);
    }

    private Notification createNotification(final User sender, final User recipient, final Long matchId, final NotificationType notificationType) {
        final Notification notification = Notification.builder()
                .notificationType(notificationType)
                .createdAt(LocalDateTime.now())
                .sender(sender)
                .recipient(recipient)
                .matchId(matchId)
                .build();
        return notificationRepository.save(notification);
    }

    public Notification createNotificationForGuest(final String senderUsername, final Long matchId, final NotificationType type) {
        final User sender = userService.getByEmail(senderUsername);
        final Match match = matchService.getById(matchId);
        final Player guest = match.getGuest();
        final User recipient = userService.getByPlayer(guest);
        return createNotification(sender, recipient, matchId, type);
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
}
