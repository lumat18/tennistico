package com.gruzini.tennistico.services.dtos;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.exceptions.EmailAlreadyExistsException;
import com.gruzini.tennistico.mappers.PlayerMapper;
import com.gruzini.tennistico.mappers.UserMapper;
import com.gruzini.tennistico.models.forms.PlayerRegistrationForm;
import com.gruzini.tennistico.models.forms.UserRegistrationForm;
import com.gruzini.tennistico.repositories.PlayerRepository;
import com.gruzini.tennistico.repositories.UserRepository;
import com.gruzini.tennistico.services.ActivationService;
import com.gruzini.tennistico.services.entities.PlayerService;
import com.gruzini.tennistico.services.entities.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationService {
    private final UserMapper userMapper;
    private final PlayerMapper playerMapper;
    private final ActivationService activationService;
    private final UserService userService;
    private final PlayerService playerService;

    public RegistrationService(final UserMapper userMapper,
                               final PlayerMapper playerMapper,
                               final ActivationService activationService,
                               final UserService userService,
                               final PlayerService playerService) {
        this.userMapper = userMapper;
        this.playerMapper = playerMapper;
        this.activationService = activationService;
        this.userService = userService;
        this.playerService = playerService;
    }

    public void register(final UserRegistrationForm userForm, final PlayerRegistrationForm playerForm) {
        final User user = mapUserAndPlayer(userForm, playerForm);
        saveUserToDatabase(user);
        activationService.sendActivationEmail(user);
    }

    private User mapUserAndPlayer(final UserRegistrationForm userForm, final PlayerRegistrationForm playerForm){
        User user = userMapper.toUser(userForm);
        Player player = playerMapper.toPlayer(playerForm);
        user.setPlayer(player);
        return user;
    }

    private void saveUserToDatabase(final User user) {
        userService.save(user);
        playerService.save(user.getPlayer());
    }

    public void validateEmailExistence(final String email) {
        userService.validateEmailExistence(email);
    }
}
