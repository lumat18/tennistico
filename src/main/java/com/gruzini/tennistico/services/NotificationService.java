package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.repositories.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    private Notification createNotificationFor(final User recipient, NotificationType notificationType){
        final Notification notification = Notification.builder()
                .recipient(recipient)
                .actionLink(createActionLink(notificationType))
                .message(notificationType.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return notificationRepository.save(notification);
    }

    private String createActionLink(final NotificationType notificationType) {
        return "http://localhost:8080/" +notificationType.getEndpoint();
    }

    public List<Notification> getByUser(String username) {
        return notificationRepository.findAllByRecipientContaining(username);
    }
}
