package com.gruzini.tennistico.services.notifications;

import com.gruzini.tennistico.events.ConfirmEvent;
import com.gruzini.tennistico.events.ConfirmScoreEvent;
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
        if (event instanceof ConfirmScoreEvent) {
            notificationService.getByMatchId(((ConfirmScoreEvent) event).getMatchId())
                    .forEach(notification -> notificationService.markAsClicked(notification.getId()));
            //SHOULD BE OK - TO TEST
        } else {
            notificationService.markAsClicked(event.getTriggerNotificationId());
        }
    }
}
