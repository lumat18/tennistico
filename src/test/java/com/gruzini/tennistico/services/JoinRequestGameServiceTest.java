package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.repositories.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JoinRequestGameServiceTest {
    @Mock
    private GameRepository gameRepository;
    @InjectMocks
    private JoinRequestGameService joinRequestGameService;

    private List<Game> games;
    private LocalDateTime dateTime;

    @BeforeEach
    void setup() {
        games = List.of(
                Game.builder().gameStatus(GameStatus.JOIN_REQUEST).build(),
                Game.builder().gameStatus(GameStatus.JOIN_REQUEST).build()
        );
        dateTime = LocalDateTime.now();
    }

    @Test
    void shouldGetAllDatesThatPassed() {
        //given
        when(gameRepository.findByStartingAtBeforeAndGameStatus(any(), eq(GameStatus.JOIN_REQUEST))).thenReturn(games);
        //when
        final List<Game> gamesThatPassed = joinRequestGameService.getAllGamesThatPassed();
        //then
        assertEquals(games, gamesThatPassed);
    }

    @Test
    void shouldChangeGameStatusToBusted() {
        //given
        when(gameRepository.save(any())).thenReturn(new Game());
        //when
        joinRequestGameService.changeGameStatusTo(games, GameStatus.BUSTED);
        //then
        assertEquals(2, games.size());
        assertTrue(games.stream()
                .allMatch(game -> game.getGameStatus().equals(GameStatus.BUSTED)));
        verify(gameRepository, times(games.size())).save(any());
    }
}