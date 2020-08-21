package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.models.dto.NotificationDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationDto toNotificationDto(Notification notification) {
        return NotificationDto.builder()
                .id(notification.getId())
                .createdAt(notification.getCreatedAt())
                .positiveEndpoint(notification.getNotificationType().getPositiveEndpoint())
                .negativeEndpoint(notification.getNotificationType().getNegativeEndpoint())
                .message(toStringMessage(notification))
                .matchId(notification.getMatchId())
                .buttonName(notification.getNotificationType().getButtonName())
                .clicked(notification.isClicked())
                .build();
    }

    private String toStringMessage(Notification notification) {
        return String.format(notification.getNotificationType().getMessage(),
                notification.getMatchInfo().getOpponentFullName(),
                notification.getMatchInfo().getScoreAsString());
    }
}
