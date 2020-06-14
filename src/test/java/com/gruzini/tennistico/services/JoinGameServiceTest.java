package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.GameStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JoinGameServiceTest {
    @Mock
    private HostedGameService hostedGameService;
    @Mock
    private PlayerService playerService;
    @InjectMocks
    private JoinGameService joinGameService;

    private Game game;
    private Player player;

    @BeforeEach
    void setup() {
        game = Game.builder()
                .id(1L)
                .players(new ArrayList<>())
                .build();
        player = Player.builder()
                .id(99L)
                .games(new ArrayList<>())
                .build();
        when(hostedGameService.getById(game.getId())).thenReturn(game);
        when(hostedGameService.save(any())).thenReturn(game);
        when(playerService.getByUsername("username")).thenReturn(player);
        when(playerService.save(any())).thenReturn(player);
    }

    @Test
    void shouldAddGuestPlayerToTheGame() {
        //given
        //when
        joinGameService.joinGuestToGame("username", 1L);
        //then
        verify(hostedGameService, times(1)).getById(game.getId());
        verify(playerService, times(1)).getByUsername("username");
        assertEquals(1, player.getGames().size());
        assertTrue(player.getGames().contains(game));
        verifyNoMoreInteractions(hostedGameService, playerService);
    }

    @Test
    void shouldChangeGameStatusToJoinRequest() {
        //given
        //when
        joinGameService.joinGuestToGame("username", 1L);
        //then
        assertEquals(GameStatus.JOIN_REQUEST, game.getGameStatus());
    }

    @Test
    void shouldSaveGameToDatabase() {
        //given
        //when
        joinGameService.joinGuestToGame("username", 1L);
        //then
        verify(hostedGameService, times(1)).save(game);
    }

    @Test
    void shouldSavePlayerToDatabase() {
        //given
        //when
        joinGameService.joinGuestToGame("username", 1L);
        //then
        verify(playerService, times(1)).save(player);
    }

}