package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.publishers.NotificationSender;
import com.gruzini.tennistico.repositories.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationSender notificationSender;
    private final UserService userService;

    public NotificationService(NotificationRepository notificationRepository, NotificationSender notificationSender, UserService userService) {
        this.notificationRepository = notificationRepository;
        this.notificationSender = notificationSender;
        this.userService = userService;
    }

    public void sendNotification(final Notification notification, final String username) {
        final User user = userService.getByEmail(username);
        notificationSender.send(notification, user);
        notificationRepository.save(notification);
    }
}
