package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.mappers.NotificationMapper;
import com.gruzini.tennistico.models.dto.NotificationDto;
import com.gruzini.tennistico.repositories.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final UserService userService;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper, UserService userService) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.userService = userService;
    }

    public Notification createNotificationFor(final User user, NotificationType notificationType){
        final Notification notification = Notification.builder()
                .recipient(user)
                .notificationType(notificationType)
                .createdAt(LocalDateTime.now())
                .build();
        log.info("Notification of type " + notificationType.toString() + " for user " + user.getEmail() + " created");
        return notificationRepository.save(notification);
    }

    public List<NotificationDto> getByUser(String username) {
        return notificationRepository.findAllByRecipientContaining(username).stream()
                .map(notificationMapper::toNotificationDto)
                .collect(Collectors.toList());
    }
}
