package com.gruzini.tennistico.bootstrap;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.Gender;
import com.gruzini.tennistico.domain.enums.PlayerSkill;
import com.gruzini.tennistico.domain.enums.UserStatus;
import com.gruzini.tennistico.repositories.PlayerRepository;
import com.gruzini.tennistico.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Profile("dev")
@Order(1)
public class UserInitialization implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;

    public UserInitialization(PasswordEncoder passwordEncoder, UserRepository userRepository, PlayerRepository playerRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        final Player player = Player.builder()
                .playerSkill(PlayerSkill.AMATEUR)
                .yearsOfExperience(3)
                .gender(Gender.MALE)
                .birthday(LocalDate.of(1970, 2, 3))
                .firstName("Pete")
                .lastName("Sampras")
                .rankingPoints(1200)
                .build();
        playerRepository.save(player);
        final User user = User.builder()
                .email("user@user.pl")
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode("pass"))
                .userStatus(UserStatus.ACTIVE)
                .player(player)
                .build();
        userRepository.save(user);
    }
}
