package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.services.entities.PlayerService;
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

}