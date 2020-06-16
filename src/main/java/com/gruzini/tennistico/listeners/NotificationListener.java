package com.gruzini.tennistico.listeners;

import com.gruzini.tennistico.events.NotificationEvent;
import com.gruzini.tennistico.services.NotificationService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    private final NotificationService notificationService;

    public NotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Async
    @EventListener
    public synchronized void handleNotificationPublish(final NotificationEvent event) {
        notificationService.saveNotification(event.getNotification());
    }
}
