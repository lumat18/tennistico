package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.models.dto.ScoreDto;
import com.gruzini.tennistico.models.dto.SetDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.nonNull;

@Component
public class ScoreMapper {

    public String mapScoreToString(final ScoreDto scoreDto) {
        final List<SetDto> setDtoList = scoreDto.getSetDtoList();
        int hostScore = 0;
        int guestScore = 0;
        for (SetDto setDTO : setDtoList) {
            if (nonNull(setDTO)) {
                if (setDTO.getHostScore() > setDTO.getGuestScore()) {
                    hostScore++;
                } else {
                    guestScore++;
                }
            }
        }
        return hostScore + "-" + guestScore;
    }
}
