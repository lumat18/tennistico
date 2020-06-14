package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.exceptions.GamePlayersException;
import com.gruzini.tennistico.exceptions.PlayerIsNotAGameHostException;
import com.gruzini.tennistico.exceptions.WrongGameStatusException;
import org.springframework.stereotype.Service;

@Service
public class ConfirmJoinService {

    private final PlayerService playerService;
    private final GameService gameService;

    public ConfirmJoinService(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    public void confirmJoin(final Long gameId, final String username) {
        final Player player = playerService.getByUsername(username);
        final Game game = gameService.getById(gameId);
        validateGameAndPlayer(game, player);
        gameService.updateGameStatus(game, GameStatus.UPCOMING);
    }

    private void validateGameAndPlayer(final Game game, final Player player) {
        validateGameStatus(game);
        validateGameHost(game, player);
        validateGamePlayers(game);
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
