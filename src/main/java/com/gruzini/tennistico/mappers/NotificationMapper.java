package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.models.dto.NotificationDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationDto toNotificationDto(Notification notification) {
        return NotificationDto.builder()
                .createdAt(notification.getCreatedAt())
                .endpoint(notification.getNotificationType().getEndpoint())
                .message(notification.getNotificationType().getMessage())
                .matchId(notification.getMatchId())
                .buttonName(notification.getNotificationType().getButtonName())
                .build();
    }
}
