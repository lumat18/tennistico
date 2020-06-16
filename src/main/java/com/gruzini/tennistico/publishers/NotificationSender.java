package com.gruzini.tennistico.publishers;

import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.events.NotificationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class NotificationSender {

    private final ApplicationEventPublisher applicationEventPublisher;

    public NotificationSender(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void send(final Notification notification, final User user) {
        final NotificationEvent event = new NotificationEvent(this, notification);
        applicationEventPublisher.publishEvent(event);
    }
}
