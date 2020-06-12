package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JoinRequestGameService {
    private final GameRepository gameRepository;

    public JoinRequestGameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllJoinRequestGamesThatPassed() {
        return gameRepository.findByStartingAtBeforeAndGameStatus(LocalDateTime.now(), GameStatus.JOIN_REQUEST);
    }
}
