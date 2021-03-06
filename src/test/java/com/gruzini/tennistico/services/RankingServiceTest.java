package com.gruzini.tennistico.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RankingServiceTest {

//    @Mock
//    private PlayerService playerService;
//    @Mock
//    private RankingPointCounter rankingPointCounter;
//
//    @InjectMocks
//    private RankingService rankingService;
//
//    private Player player;
//
//    @BeforeEach
//    void setup() {
//        player = Player.builder()
//                .rankingPoints(0)
//                .build();
//
//        when(playerService.save(any())).thenReturn(player);
//    }

//    @Test
//    void shouldIncreaseWinnerWins() {
//        //given
//        final int wins = player.getRanking().getWins();
//        //when
//        rankingService.updateWinner(player);
//        //then
//        assertTrue(player.getRanking().getWins() > wins);
//    }
//
//    @Test
//    void shouldChangeWinnerRankingPoints() {
//        //given
//        final int rankingPoints = player.getRanking().getRankingPoints();
//        when(rankingPointCounter.calculateWinPoints()).thenReturn(rankingPoints + 1);
//        //when
//        rankingService.updateWinner(player);
//        //then
//        verify(rankingPointCounter, times(1)).calculateWinPoints();
//        assertEquals(rankingPoints + 1, player.getRanking().getRankingPoints());
//        verifyNoMoreInteractions(rankingPointCounter);
//    }
//
//    @Test
//    void shouldIncreaseLoserLosses() {
//        //given
//        final int losses = player.getRanking().getLosses();
//        //when
//        rankingService.updateLoser(player);
//        //then
//        assertTrue(player.getRanking().getLosses() > losses);
//    }
//
//    @Test
//    void shouldChangeLoserRankingPoints() {
//        //given
//        final int rankingPoints = player.getRanking().getRankingPoints();
//        when(rankingPointCounter.calculateLossPoints()).thenReturn(rankingPoints + 1);
//        //when
//        rankingService.updateLoser(player);
//        //then
//        verify(rankingPointCounter, times(1)).calculateLossPoints();
//        assertEquals(rankingPoints + 1, player.getRanking().getRankingPoints());
//        verifyNoMoreInteractions(rankingPointCounter);
//    }
}