package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Notification;
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

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    public Notification createNotification(final User sender, final User recipient, final Long matchId, final NotificationType notificationType) {
        final Notification notification = Notification.builder()
                .notificationType(notificationType)
                .createdAt(LocalDateTime.now())
                .sender(sender)
                .recipient(recipient)
                .matchId(matchId)
                .build();
        return notificationRepository.save(notification);
    }

    public Notification createNotification(final User recipient, final NotificationType notificationType) {
        return createNotification(null, recipient, null, notificationType);
    }

    public List<NotificationDto> getByUser(String username) {
        return notificationRepository.findAllByRecipient(username).stream()
                .map(notificationMapper::toNotificationDto)
                .collect(Collectors.toList());
    }
}
