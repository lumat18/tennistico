package com.gruzini.tennistico.services.notifications;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificationUpdateListener {
    private final NotificationService notificationService;

    public NotificationUpdateListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @EventListener
    public void handleEvent(final ConfirmEvent event) {
        notificationService.markAsClicked(event.getTriggerNotificationId());
    }
}
