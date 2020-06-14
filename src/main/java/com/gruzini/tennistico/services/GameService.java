package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.exceptions.GameNotFoundException;
import com.gruzini.tennistico.repositories.GameRepository;
import com.gruzini.tennistico.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;


    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public Game save(final Game game) {
        return gameRepository.save(game);
    }

    public void updateGameStatus(final List<Game> games, final GameStatus gameStatus) {
        games.forEach(game -> updateGameStatus(game, gameStatus));
    }

    public void updateGameStatus(final Game game, final GameStatus gameStatus) {
        game.setGameStatus(gameStatus);
        gameRepository.save(game);
        game.getPlayers().forEach(playerRepository::save);
    }

    public Game getById(final Long id) {
        return gameRepository.findById(id).orElseThrow(GameNotFoundException::new);
    }

    public List<Game> getHostedGamesExceptHostedBy(final Player player) {
        return gameRepository.findAllByGameStatusAndPlayersNotContainsAndStartingAtIsAfterOrderByStartingAt(GameStatus.HOSTED, player, LocalDateTime.now());
    }

    public List<Game> getByPlayerAndStatus(final Player player, final GameStatus gameStatus) {
        return gameRepository.getAllByPlayersAndGameStatus(player, gameStatus);
    }

    public List<Game> getAllThatStartingDateTimePassedByStatus(final GameStatus gameStatus) {
        return gameRepository.findByStartingAtBeforeAndGameStatus(LocalDateTime.now(), gameStatus);
    }
}
