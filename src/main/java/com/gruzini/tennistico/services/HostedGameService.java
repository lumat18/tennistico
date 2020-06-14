package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.exceptions.GameNotFoundException;
import com.gruzini.tennistico.mappers.HostedGameMapper;
import com.gruzini.tennistico.models.dto.HostedGameDto;
import com.gruzini.tennistico.repositories.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class HostedGameService {
    private final GameRepository gameRepository;
    private final HostedGameMapper gameInfoMapper;

    public HostedGameService(GameRepository gameRepository, HostedGameMapper gameInfoMapper) {
        this.gameRepository = gameRepository;
        this.gameInfoMapper = gameInfoMapper;
    }

    public List<HostedGameDto> getAll(Player player) {
        final List<Game> result = gameRepository.findAllByGameStatusAndPlayersNotContainsAndStartingAtIsAfterOrderByStartingAt(GameStatus.HOSTED, player, LocalDateTime.now());
        return result.stream()
                .map(gameInfoMapper::toGameInfoDto)
                .collect(Collectors.toList());
    }

    public List<Game> getAllGamesThatPassed() {
        return gameRepository.findByStartingAtBeforeAndGameStatus(LocalDateTime.now(), GameStatus.HOSTED);
    }

    public void changeGameStatusTo(List<Game> games, GameStatus gameStatus) {
        games.forEach(game -> {
            game.setGameStatus(gameStatus);
            gameRepository.save(game);
        });
    }

    public Game getById(final Long gameId) {
        return gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
    }

    public Game save(final Game game) {
        return gameRepository.save(game);

    }
}
