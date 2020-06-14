package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.GameStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JoinGameService {

    private final HostedGameService hostedGameService;
    private final PlayerService playerService;

    public JoinGameService(HostedGameService hostedGameService, PlayerService playerService) {
        this.hostedGameService = hostedGameService;
        this.playerService = playerService;
    }

    public void joinGuestToGame(final String guestUsername, final Long gameId) {
        final Game game = changeGameStatus(gameId);
        final Player guest = playerService.getByUsername(guestUsername);
        addGameToPlayer(guest, game);
    }

    private Game changeGameStatus(Long gameId) {
        final Game game = hostedGameService.getById(gameId);
        game.setGameStatus(GameStatus.JOIN_REQUEST);
        hostedGameService.save(game);
        return game;
    }

    private void addGameToPlayer(Player guest, Game game) {
        guest.addGame(game);
        playerService.save(guest);
    }
}
