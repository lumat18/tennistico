package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.models.dto.CreatedMatchDto;
import com.gruzini.tennistico.services.CourtService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreatedMatchMapper {
    private final CourtService courtService;

    public CreatedMatchMapper(CourtService courtService) {
        this.courtService = courtService;
    }

    public Match toMatch(final CreatedMatchDto createdMatchDto) {
        return Match.builder()
                .matchStatus(MatchStatus.HOSTED)
                .isOpen(true)
                .court(courtService.getById(createdMatchDto.getCourtId()))
                .startingAt(LocalDateTime.of(createdMatchDto.getDate(), createdMatchDto.getStart()))
                .endingAt(LocalDateTime.of(createdMatchDto.getDate(), createdMatchDto.getEnd()))
                .build();
    }
}
