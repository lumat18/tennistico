package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import com.gruzini.tennistico.models.dto.NotificationDto;
import com.gruzini.tennistico.services.entity_related.MatchService;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class NotificationMapper {
    private final MatchService matchService;

    public NotificationMapper(MatchService matchService) {
        this.matchService = matchService;
    }

    public NotificationDto toNotificationDto(Notification notification) {
        return NotificationDto.builder()
                .id(notification.getId())
                .createdAt(notification.getCreatedAt())
                .endpoint(notification.getNotificationType().getEndpoint())
                .message(String.format(notification.getNotificationType().getMessage(), getOpponentName(notification, notification.getRecipient().getPlayer())))
                .matchId(notification.getMatchId())
                .buttonName(notification.getNotificationType().getButtonName())
                .clicked(notification.isClicked())
                .build();
    }
    private String getOpponentName(final Notification notification, final Player player){
        final Long matchId = notification.getMatchId();
        if (isNull(matchId)){
            return "";
        }
        final Match match = matchService.getById(matchId);
        Player opponent;
        if (match.getHost().equals(player)){
            opponent = match.getGuest().orElseThrow(PlayerNotFoundException::new);
        }else {
            opponent = match.getHost();
        }
        return opponent.getFirstName() + " " + opponent.getLastName();
    }
}
