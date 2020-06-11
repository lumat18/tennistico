package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.repositories.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class GameServiceTest {

    @MockBean
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;

    @Test
    void shouldFindOneGameByStatus() {
        //given
        final Game game = Game.builder().gameStatus(GameStatus.UPCOMING).build();
        when(gameRepository.findAllByGameStatusOrderByStartingAt(any())).thenReturn(List.of(game));
        //when
        final List<Game> result = gameService.getAllUpcoming();
        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getGameStatus().toString()).isEqualTo(GameStatus.UPCOMING.toString());
    }

    @Test
    void shouldNotFindUpcomingGameByStatusIfDoesNotExistInRepo() {
        //given
        final Game game = Game.builder().gameStatus(GameStatus.UPCOMING).build();
        when(gameRepository.findAllByGameStatusOrderByStartingAt(any())).thenReturn(List.of());
        //when
        final List<Game> result = gameService.getAllUpcoming();
        //then
        assertThat(result.size()).isEqualTo(0);
    }

}