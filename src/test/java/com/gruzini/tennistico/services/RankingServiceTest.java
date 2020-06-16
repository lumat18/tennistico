package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.Ranking;
import com.gruzini.tennistico.models.score.RankingPointCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class RankingServiceTest {

    @Mock
    private PlayerService playerService;
    @Mock
    private RankingPointCounter rankingPointCounter;

    @InjectMocks
    private RankingService rankingService;

    private Player player;

    @BeforeEach
    void setup() {
        player = Player.builder()
                .ranking(new Ranking())
                .build();

        doNothing().when(playerService).save(any());
    }

    @Test
    void shouldIncreaseWinnerWins() {
        //given
        final int wins = player.getRanking().getWins();
        //when
        rankingService.updateWinner(player);
        //then
        assertTrue(player.getRanking().getWins() > wins);
    }

    @Test
    void shouldChangeWinnerRankingPoints() {
        //given
        //when
        //then
    }

    @Test
    void shouldIncreaseLoserLosses() {
        //given
        //when
        //then
    }

    @Test
    void shouldChangeLoserRankingPoints() {
        //given
        //when
        //then
    }
}