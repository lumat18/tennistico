package com.gruzini.tennistico.checkers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FutureGameCheckerTest {
//    @Mock
//    private UpcomingGameService upcomingGameService;
//    @Mock
//    private HostedGameService hostedGameService;
//    @Mock
//    private JoinRequestGameService joinRequestGameService;
//    @InjectMocks
//    private FutureGameChecker futureGameChecker;
//
//    private List<Game> games;
//
//    @BeforeEach
//    void setup() {
//        games = new ArrayList<>();
//    }
//
//    @Test
//    void shouldChangeGameStatusFromUpcomingToPending() {
//        //given
//        when(upcomingGameService.getAllGamesThatPassed()).thenReturn(games);
//        doNothing().when(upcomingGameService).changeGameStatusTo(games, GameStatus.PENDING);
//        //when
//        futureGameChecker.updateFutureGamesStatus();
//        //then
//        verify(upcomingGameService, times(1)).getAllGamesThatPassed();
//        verify(upcomingGameService, times(1)).changeGameStatusTo(games, GameStatus.PENDING);
//    }
//
//    @Test
//    void shouldChangeGameStatusFromHostedToBusted() {
//        //given
//        when(hostedGameService.getAllGamesThatPassed()).thenReturn(games);
//        doNothing().when(hostedGameService).changeGameStatusTo(games, GameStatus.BUSTED);
//        //when
//        futureGameChecker.updateFutureGamesStatus();
//        //then
//        verify(hostedGameService, times(1)).getAllGamesThatPassed();
//        verify(hostedGameService, times(1)).changeGameStatusTo(games, GameStatus.BUSTED);
//    }
//
//    @Test
//    void shouldChangeGameStatusFromJoinRequestToBusted() {
//        //given
//        when(joinRequestGameService.getAllGamesThatPassed()).thenReturn(games);
//        doNothing().when(joinRequestGameService).changeGameStatusTo(games, GameStatus.BUSTED);
//        //when
//        futureGameChecker.updateFutureGamesStatus();
//        //then
//        verify(joinRequestGameService, times(1)).getAllGamesThatPassed();
//        verify(joinRequestGameService, times(1)).changeGameStatusTo(games, GameStatus.BUSTED);
//    }
}