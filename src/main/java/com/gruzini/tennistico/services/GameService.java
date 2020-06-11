package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.repositories.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAll(){
        return gameRepository.findAll();
    }
    public List<Game> getAllUpcoming(){
        final List<Game> result = gameRepository.findAllByGameStatus(GameStatus.UPCOMING);
        log.info("found "+result.size()+" upcoming games");
        return result;
    }
}
