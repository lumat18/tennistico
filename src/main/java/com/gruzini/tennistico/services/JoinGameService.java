package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.GameStatus;
import org.springframework.stereotype.Service;

@Service
public class JoinGameService {

    private final HostedGameService hostedGameService;
    private final PlayerService playerService;

    public JoinGameService(HostedGameService hostedGameService, PlayerService playerService) {
        this.hostedGameService = hostedGameService;
        this.playerService = playerService;
    }

    public void joinGuestToGame(final String guestUsername, final Long gameId){
        final Game game = hostedGameService.getById(gameId);
        final Player guest = playerService.getByUsername(guestUsername);
        game.addGuest(guest);
        game.setGameStatus(GameStatus.JOIN_REQUEST);
        hostedGameService.save(game);
    }
}
