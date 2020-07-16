package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.Score;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import com.gruzini.tennistico.models.dto.PlayerDto;
import com.gruzini.tennistico.models.dto.matchDto.ArchivedMatchDto;
import org.springframework.stereotype.Component;

@Component
public class ArchivedMatchMapper {

    private final PlayerDtoMapper playerDtoMapper;

    public ArchivedMatchMapper(final PlayerDtoMapper playerDtoMapper) {
        this.playerDtoMapper = playerDtoMapper;
    }

    public ArchivedMatchDto toArchivedMatchDTO(final Match match, final Player player) {
        return ArchivedMatchDto.builder()
                .opponent(getOpponent(match, player))
                .score(getScore(match.getScore()))
                .courtName(match.getCourt().getName() + ", " + match.getCourt().getCity())
                .date(match.getEndingAt().toLocalDate())
                .build();
    }

    private PlayerDto getOpponent(final Match match, final Player player){
        Player opponent;
        if (match.getHost().equals(player)){
            opponent = match.getGuest().orElseThrow(PlayerNotFoundException::new);
        } else {
            opponent = match.getHost();
        }
        return playerDtoMapper.toPlayerDto(opponent);
    }

    private String getScore(final Score score) {
        StringBuilder stringBuilder = new StringBuilder();
        score.getSets().forEach(set -> {
            stringBuilder.append(set.getHostScore()).append(" - ").append(set.getGuestScore()).append("  ");
        });
        return stringBuilder.toString();
    }
}
