package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.mappers.CreatedGameMapper;
import com.gruzini.tennistico.models.dto.CreatedGameDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class GameCreationService {

    private final CreatedGameMapper createdGameMapper;
    private final GameService gameService;
    private final PlayerService playerService;
    private final UserService userService;

    public GameCreationService(CreatedGameMapper createdGameMapper, GameService gameService, PlayerService playerService, UserService userService) {
        this.createdGameMapper = createdGameMapper;
        this.gameService = gameService;
        this.playerService = playerService;
        this.userService = userService;
    }

    public void create(final CreatedGameDto createdGameDto, final String username){
        final Player player = userService.getByEmail(username).getPlayer();
        final Game createdGame = saveCreatedGame(createdGameDto);
        playerService.add(player, createdGame);
    }

    private Game saveCreatedGame(CreatedGameDto createdGameDto) {
        final Game createdGame = createdGameMapper.toGame(createdGameDto);
        gameService.save(createdGame);
        return createdGame;
    }
}
