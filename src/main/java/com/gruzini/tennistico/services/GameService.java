package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.mappers.HostedGameMapper;
import com.gruzini.tennistico.models.dto.HostedGameDto;
import com.gruzini.tennistico.repositories.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class GameService {
    private final GameRepository gameRepository;
    private final HostedGameMapper gameInfoMapper;

    public GameService(GameRepository gameRepository, HostedGameMapper gameInfoMapper) {
        this.gameRepository = gameRepository;
        this.gameInfoMapper = gameInfoMapper;
    }

    public List<HostedGameDto> getAll() {
        final List<Game> result = gameRepository.findAllByGameStatusOrderByStartingAt(GameStatus.HOSTED);
        return result.stream()
                .map(gameInfoMapper::toGameInfoDto)
                .collect(Collectors.toList());
    }

    public Game save(Game game) {
        return gameRepository.save(game);
    }
}