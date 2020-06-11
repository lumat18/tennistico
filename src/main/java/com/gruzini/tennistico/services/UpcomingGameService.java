package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.mappers.UpcomingGameMapper;
import com.gruzini.tennistico.models.dto.UpcomingGameDto;
import com.gruzini.tennistico.repositories.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UpcomingGameService {
    private final GameRepository gameRepository;
    private final UpcomingGameMapper gameInfoMapper;

    public UpcomingGameService(GameRepository gameRepository, UpcomingGameMapper gameInfoMapper) {
        this.gameRepository = gameRepository;
        this.gameInfoMapper = gameInfoMapper;
    }

    public List<UpcomingGameDto> getAll() {
        final List<Game> result = gameRepository.findAllByGameStatusOrderByStartingAt(GameStatus.UPCOMING);
        return result.stream()
                .map(gameInfoMapper::toGameInfoDto)
                .collect(Collectors.toList());
    }
}
