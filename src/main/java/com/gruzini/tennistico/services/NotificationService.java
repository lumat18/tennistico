package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.repositories.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    private Notification createNotificationFor(final List<User> recipients, NotificationType notificationType){
        final Notification notification = Notification.builder()
                .recipients(recipients)
                .actionLink(createActionLink(notificationType))
                .message(notificationType.getMessage())
                .build();
        return notificationRepository.save(notification);
    }

    private String createActionLink(final NotificationType notificationType) {
        return "http://localhost:8080/" +notificationType.getEndpoint();
    }
}
