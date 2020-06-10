package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.ActivationToken;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.repositories.ActivationTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ActivationTokenServiceTest {
    @MockBean
    private ActivationTokenRepository activationTokenRepository;

    @Autowired
    private ActivationTokenService activationTokenService;

    @Captor
    private ArgumentCaptor<ActivationToken> captor;

    private ActivationToken activationToken;

    @BeforeEach
    void setup() {
        activationToken = ActivationToken.builder()
                .value("token")
                .user(User.builder()
                        .email("user@user.com")
                        .password("password")
                        .build())
                .build();
    }

    @Test
    void shouldCreateActivationToken() {
        //when
        final ActivationToken token = activationTokenService.createToken();
        //then
        assertThat(token).isInstanceOf(ActivationToken.class);
        assertThat(token.getValue()).isNotEmpty();
    }

    @Test
    void shouldSaveTokenToDatabase() {
        //given
        when(activationTokenRepository.save(activationToken)).thenReturn(activationToken);
        //when
        activationTokenService.saveToken(activationToken);
        //then
        verify(activationTokenRepository).save(captor.capture());
        assertThat(activationToken).isEqualTo(captor.getValue());
    }
}