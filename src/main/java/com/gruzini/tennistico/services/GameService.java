package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.exceptions.GameNotFoundException;
import com.gruzini.tennistico.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game getById(final Long id) {
        return gameRepository.findById(id).orElseThrow(GameNotFoundException::new);
    }
}
