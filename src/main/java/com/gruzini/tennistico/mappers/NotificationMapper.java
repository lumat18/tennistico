package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.*;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import com.gruzini.tennistico.models.dto.NotificationDto;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.ScoreService;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class NotificationMapper {
    private final MatchService matchService;
    private final ScoreService scoreService;

    public NotificationMapper(MatchService matchService, ScoreService scoreService) {
        this.matchService = matchService;
        this.scoreService = scoreService;
    }

    public NotificationDto toNotificationDto(Notification notification) {
        return NotificationDto.builder()
                .id(notification.getId())
                .createdAt(notification.getCreatedAt())
                .endpoint(notification.getNotificationType().getEndpoint())
                .message(
                        String.format(notification.getNotificationType().getMessage(),
                                getOpponentName(notification, notification.getRecipient().getPlayer()),
                                getScore(notification.getMatchId())))
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

    private String getScore(final Long matchId) {
        if (isNull(matchId)){
            return "";
        }
        final Score score = matchService.getById(matchId).getScore();
        if (isNull(score)){
            return "";
        }
        final List<Set> sets = scoreService.getById(score.getId()).getSets();
        StringBuilder stringBuilder = new StringBuilder();
        sets.forEach(set -> {
            stringBuilder.append(set.getGuestScore() + " - " + set.getHostScore());
            if (!set.equals(sets.get(sets.size()-1))){
                stringBuilder.append(" , ");
            }
        });
        return stringBuilder.toString();
    }
}
