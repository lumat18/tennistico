package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.models.dto.CreatedGameDto;
import com.gruzini.tennistico.services.CourtService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreatedGameMapper {
    private final CourtService courtService;

    public CreatedGameMapper(CourtService courtService) {
        this.courtService = courtService;
    }

    public Game toGame(final CreatedGameDto createdGameDto){
        return Game.builder()
                .gameStatus(GameStatus.HOSTED)
                .isOpen(true)
                .court(courtService.getById(createdGameDto.getCourtId()))
//                .startingAt(LocalDateTime.of(createdGameDto.getDate(), createdGameDto.getStart()))
//                .endingAt(LocalDateTime.of(createdGameDto.getDate(), createdGameDto.getEnd()))
                .build();
    }
}
