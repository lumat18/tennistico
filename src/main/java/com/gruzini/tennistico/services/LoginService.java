package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.exceptions.EmailAlreadyExistsException;
import com.gruzini.tennistico.exceptions.LoginCredentialsInvalidException;
import com.gruzini.tennistico.models.forms.UserLoginForm;
import com.gruzini.tennistico.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {
   private static final String LOGIN_EXCEPTION_MESSAGE = "Email or password are incorrect";
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

   public LoginService(final UserRepository userRepository,
                       final PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
   }

   public void validateCredentials(final UserLoginForm userLoginForm) {
      if(userRepository.findByEmail(userLoginForm.getEmail()).isEmpty()){
         throw new LoginCredentialsInvalidException(LOGIN_EXCEPTION_MESSAGE);
      }
      final User user = userRepository.findByEmail(userLoginForm.getEmail()).get();
      if(!user.getPassword().equals(passwordEncoder.encode(userLoginForm.getPassword()))){
         throw new LoginCredentialsInvalidException(LOGIN_EXCEPTION_MESSAGE);
      }
   }
}
