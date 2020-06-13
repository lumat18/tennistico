package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.exceptions.GamePlayersException;
import com.gruzini.tennistico.exceptions.PlayerIsNotAGameHostException;
import com.gruzini.tennistico.exceptions.WrongGameStatusException;
import org.springframework.stereotype.Service;

@Service
public class JoinConfirmService {

    private final PlayerService playerService;
    private final GameService gameService;

    public JoinConfirmService(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    public void confirmJoin(final Long gameId, final String username) {
        final Player player = playerService.getByUsername(username);
        final Game game = gameService.getById(gameId);
        validateGameStatus(game);
        validateGameHost(game, player);
        validateGamePlayers(game);
        gameService.setGameStatusTo(game, GameStatus.UPCOMING);
    }

    private void validateGamePlayers(final Game game) {
        if (game.getPlayers().size() != 2) {
            throw new GamePlayersException();
        }
    }

    private void validateGameStatus(final Game game) {
        if (!game.getGameStatus().equals(GameStatus.JOIN_REQUEST)) {
            throw new WrongGameStatusException();
        }
    }

    private void validateGameHost(final Game game, final Player player) {
        if (!game.getHost().equals(player)) {
            throw new PlayerIsNotAGameHostException();
        }
    }
}
