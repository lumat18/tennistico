package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.forms.UserRegistrationForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserMapper userMapper;

    @Test
    void shouldMapUserRegistrationToUser() {
        //given
        final UserRegistrationForm userRegistrationForm =
                new UserRegistrationForm("email@email.com", "abc", "abc");
        when(passwordEncoder.encode(userRegistrationForm.getPassword())).thenReturn(userRegistrationForm.getPassword());
        //when
        final User user = userMapper.toUser(userRegistrationForm);
        //then
        assertAll(() -> {
            assertEquals(userRegistrationForm.getEmail(), user.getEmail());
            assertEquals(userRegistrationForm.getPassword(), user.getPassword());
        });
        verify(passwordEncoder, times(1)).encode(userRegistrationForm.getPassword());
        verifyNoMoreInteractions(passwordEncoder);
    }
}