package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.*;
import com.gruzini.tennistico.models.dto.NotificationDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    private final MatchInfoParser matchInfoParser;

    public NotificationMapper(MatchInfoParser matchInfoParser) {
        this.matchInfoParser = matchInfoParser;
    }

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
                matchInfoParser.getOpponentName(notification.getMatchId(), notification.getRecipient().getPlayer()),
                matchInfoParser.getScore(notification.getMatchId(), notification.getRecipient().getPlayer()));
    }


}
