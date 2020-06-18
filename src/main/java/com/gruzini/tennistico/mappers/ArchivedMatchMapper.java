package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.Score;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import com.gruzini.tennistico.models.dto.matchDto.ArchivedMatchDto;
import org.springframework.stereotype.Component;

@Component
public class ArchivedMatchMapper {
    public ArchivedMatchDto toArchivedMatchDTO(final Match match, final Player player) {
        return ArchivedMatchDto.builder()
                .opponentName(getOpponentName(match, player))
                .score(getScore(match.getScore()))
                .courtName(match.getCourt().getName() + ", " + match.getCourt().getCity())
                .date(match.getEndingAt().toLocalDate())
                .build();
    }

    private String getOpponentName(final Match match, final Player player){
        Player opponent;
        if (match.getHost().equals(player)){
            opponent = match.getGuest().orElseThrow(PlayerNotFoundException::new);
        }else {
            opponent = match.getHost();
        }
        return opponent.getFirstName() + " " + opponent.getLastName();
    }

    private String getScore(final Score score) {
        StringBuilder stringBuilder = new StringBuilder();
        score.getSets().forEach(set -> {
            stringBuilder.append(set.getHostScore() + " - " + set.getGuestScore() + "  ");
        });
        return stringBuilder.toString();
    }
}
