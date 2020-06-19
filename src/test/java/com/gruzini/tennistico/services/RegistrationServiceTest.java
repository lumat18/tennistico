package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.exceptions.EmailAlreadyExistsException;
import com.gruzini.tennistico.repositories.PlayerRepository;
import com.gruzini.tennistico.repositories.UserRepository;
import com.gruzini.tennistico.services.dtos.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@SpringBootTest
class RegistrationServiceTest {
//    @MockBean
//    private UserRepository userRepository;
//    @MockBean
//    private PlayerRepository playerRepository;
//    @MockBean
//    private ActivationService activationService;
//
//    @Autowired
//    private RegistrationService registrationService;
//
//    private User user;
//
//    @BeforeEach
//    void setup() {
//        this.user = User.builder()
//                .email("user@user.com")
//                .password("abc")
//                .player(Player.builder()
//                        .firstName("name")
//                        .build())
//                .build();
//    }
//
//    @Test
//    void shouldSaveNewUserToDatabase() {
//        //given
//        when(userRepository.save(user)).thenReturn(user);
//        when(playerRepository.save(user.getPlayer())).thenReturn(user.getPlayer());
//        //when
//        registrationService.register(user);
//        //then
//        verify(userRepository, times(1)).save(user);
//        verify(playerRepository, times(1)).save(user.getPlayer());
//    }
//
//    @Test
//    void shouldSendActivationEmail() {
//        //given
//        doNothing().when(activationService).sendActivationEmail(user);
//        //when
//        registrationService.register(user);
//        //then
//        verify(activationService, times(1)).sendActivationEmail(user);
//    }
//
//    @Test
//    void shouldThrowWhenValidatingExistingEmail() {
//        //given
//        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
//        //when then
//        assertThatExceptionOfType(EmailAlreadyExistsException.class)
//                .isThrownBy(() -> registrationService.validateEmailExistence(user.getEmail()));
//    }

}