package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.services.GameService;
import com.gruzini.tennistico.services.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/confirm-join")
public class ConfirmJoinController {
    private final PlayerService playerService;
    private final GameService gameService;

    public ConfirmJoinController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @GetMapping("/{game_id}")
    public String confirmGuestJoiningTheGame(@PathVariable(name = "game_id") final Long gameId, final Principal principal) {
        //TODO wyciągnąć grę, sprawdzić czy host to principal, jak nie to rzucić exception, ewentualnie sprawdzić, czy są 2 gracze i zmienić status na UPCOMING
        //TODO wysłać notification do Guesta
        final Player player = playerService.getByUsername(principal.getName());
        final Game game = gameService.getById(gameId);

        if (isCurrentPlayerAHost(player, game)) {

        }
        return "dashboard";
    }

    private void validateGameStatus(final Game game) {
        game.getGameStatus().equals(GameStatus.JOIN_REQUEST);
    }

    private boolean isCurrentPlayerAHost(final Player player, final Game game) {
        return game.getHost().equals(player);
    }
}
