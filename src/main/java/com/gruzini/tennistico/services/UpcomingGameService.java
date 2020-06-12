package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UpcomingGameService implements GameService {

    private final GameRepository gameRepository;

    public UpcomingGameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> getAllGamesThatPassed() {
        return gameRepository.findByStartingAtBeforeAndGameStatus(LocalDateTime.now(), GameStatus.UPCOMING);
    }

    @Override
    public void changeGameStatusTo(List<Game> games, GameStatus gameStatus) {
        games.forEach(game -> {
            game.setGameStatus(gameStatus);
            gameRepository.save(game);
        });
    }
}
