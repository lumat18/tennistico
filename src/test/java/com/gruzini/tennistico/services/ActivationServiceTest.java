package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.ActivationToken;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.emails.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ActivationServiceTest {
    @MockBean
    private ActivationTokenService activationTokenService;
    @MockBean
    private EmailService emailService;

    @Autowired
    private ActivationService activationService;

    private User user;
    private ActivationToken token;

    @Captor
    private ArgumentCaptor<String> captor;
    @Captor
    private ArgumentCaptor<ActivationToken> tokenCaptor;

    @BeforeEach
    void setupEntities() {
        this.user = User.builder()
                .email("user@user.com")
                .password("password")
                .player(Player.builder()
                        .firstName("name")
                        .build())
                .build();

        this.token = ActivationToken.builder()
                .value("token")
                .user(user)
                .build();
    }

    @BeforeEach
    void setupMocksBehavior() {
        //given
        when(activationTokenService.createToken()).thenReturn(token);
        doNothing().when(activationTokenService).saveToken(token);
        doNothing().when(emailService).sendEmail(user.getEmail(), token.getValue(), MessageType.ACTIVATION);
    }

    @Test
    void shouldSendActivationEmailToUser() {
        //when
        activationService.sendActivationEmail(user);
        //then
        verify(emailService).sendEmail(captor.capture(), captor.capture(), any());
        assertEquals(user.getEmail(), captor.getAllValues().get(0));
        assertEquals(token.getValue(), captor.getAllValues().get(1));
        verify(activationTokenService, times(1)).createToken();
        verify(activationTokenService, times(1)).saveToken(any());
    }

    @Test
    void shouldAssignUserToToken() {
        //when
        activationService.sendActivationEmail(user);
        //then
        verify(activationTokenService).saveToken(tokenCaptor.capture());
        assertEquals(user, tokenCaptor.getValue().getUser());
    }
}