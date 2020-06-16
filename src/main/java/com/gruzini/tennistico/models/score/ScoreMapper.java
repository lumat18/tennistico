package com.gruzini.tennistico.models.score;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.nonNull;

@Component
public class ScoreMapper {

    public String mapScoreToString(final ScoreDTO scoreDTO) {
        final List<SetDTO> setDTOS = scoreDTO.getSetDtoList();
        int hostScore = 0;
        int guestScore = 0;
        for (SetDTO setDTO : setDTOS) {
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
