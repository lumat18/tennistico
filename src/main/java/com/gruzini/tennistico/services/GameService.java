package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.enums.GameStatus;

import java.util.List;

public interface GameService {
    List<Game> getAllGamesThatPassed();

    void changeGameStatusTo(final List<Game> games, final GameStatus gameStatus);
}
