package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.exceptions.GameNotFoundException;
import com.gruzini.tennistico.repositories.GameRepository;
import com.gruzini.tennistico.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public Game getById(final Long id) {
        return gameRepository.findById(id).orElseThrow(GameNotFoundException::new);
    }

    public Game setGameStatusTo(final Game game, final GameStatus gameStatus) {
        game.setGameStatus(gameStatus);
        gameRepository.save(game);
        game.getPlayers().forEach(playerRepository::save);
        return game;
    }
}
