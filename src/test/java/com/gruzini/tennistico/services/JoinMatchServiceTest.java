package com.gruzini.tennistico.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JoinMatchServiceTest {
//    @Mock
//    private MatchService matchService;
//    @Mock
//    private PlayerService playerService;
//    @InjectMocks
//    private JoinMatchService joinMatchService;
//
//    private Match match;
//    private Player player;
//
//    @BeforeEach
//    void setup() {
//        match = Match.builder()
//                .id(1L)
//                .players(new ArrayList<>())
//                .build();
//        player = Player.builder()
//                .id(99L)
//                .matches(new ArrayList<>())
//                .build();
//        when(matchService.getById(match.getId())).thenReturn(match);
//        when(matchService.save(any())).thenReturn(match);
//        when(playerService.getByUsername("username")).thenReturn(player);
//        when(playerService.save(any())).thenReturn(player);
//    }
//
//    @Test
//    void shouldAddGuestPlayerToTheMatch() {
//        //given
//        //when
//        joinMatchService.joinGuestToMatch("username", 1L);
//        //then
//        verify(matchService, times(1)).getById(match.getId());
//        verify(playerService, times(1)).getByUsername("username");
//        assertEquals(1, player.getMatches().size());
//        assertTrue(player.getMatches().contains(match));
//        verifyNoMoreInteractions(matchService, playerService);
//    }
//
//    @Test
//    void shouldChangeMatchStatusToJoinRequest() {
//        //given
//        //when
//        joinMatchService.joinGuestToMatch("username", 1L);
//        //then
//        assertEquals(MatchStatus.JOIN_REQUEST, match.getMatchStatus());
//    }
//
//    @Test
//    void shouldSaveMatchToDatabase() {
//        //given
//        //when
//        joinMatchService.joinGuestToMatch("username", 1L);
//        //then
//        verify(matchService, times(1)).save(match);
//    }
//
//    @Test
//    void shouldSavePlayerToDatabase() {
//        //given
//        //when
//        joinMatchService.joinGuestToMatch("username", 1L);
//        //then
//        verify(playerService, times(1)).save(player);
//    }

//    private Match match;
//    private Player player;
//
//    @BeforeEach
//    void setup() {
//        match = Match.builder()
//                .id(1L)
//                .build();
//        player = Player.builder()
//                .id(99L)
//                .build();
//        when(matchService.getById(match.getId())).thenReturn(match);
//        when(matchService.save(any())).thenReturn(match);
//        when(playerService.getByUsername("username")).thenReturn(player);
//        when(playerService.save(any())).thenReturn(player);
//    }
//
//    @Test
//    void shouldAddGuestPlayerToTheMatch() {
//        //given
//        //when
//        joinMatchService.joinGuestToMatch("username", 1L);
//        //then
//        verify(matchService, times(1)).getById(match.getId());
//        verify(playerService, times(1)).getByUsername("username");
//        assertEquals(player, match.getGuest());
//        verifyNoMoreInteractions(matchService, playerService);
//    }
//
//    @Test
//    void shouldChangeMatchStatusToJoinRequest() {
//        //given
//        //when
//        joinMatchService.joinGuestToMatch("username", 1L);
//        //then
//        assertEquals(MatchStatus.JOIN_REQUEST, match.getMatchStatus());
//    }
//
//    @Test
//    void shouldSaveMatchToDatabase() {
//        //given
//        //when
//        joinMatchService.joinGuestToMatch("username", 1L);
//        //then
//        verify(matchService, times(1)).save(match);
//    }
//
//    @Test
//    void shouldSavePlayerToDatabase() {
//        //given
//        //when
//        joinMatchService.joinGuestToMatch("username", 1L);
//        //then
//        verify(playerService, times(1)).save(player);
//    }

}