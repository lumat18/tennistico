package com.gruzini.tennistico.bootstrap;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.PlayerMatchResults;
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
import java.util.List;

@Component
@Profile("dev")
@Order(1)
public class UserAndPlayerInitializer implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;

    public UserAndPlayerInitializer(PasswordEncoder passwordEncoder, UserRepository userRepository, PlayerRepository playerRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        final Player player1 = Player.builder()
                .firstName("Pete")
                .lastName("Sampras")
                .gender(Gender.MALE)
                .birthday(LocalDate.of(1970, 2, 3))
                .yearsOfExperience(3)
                .playerSkill(PlayerSkill.AMATEUR)
                .rankingPoints(1800)
                .matchResults(new PlayerMatchResults(0,0,0))
                .build();
        final Player player2 = Player.builder()
                .firstName("Roger")
                .lastName("Federer")
                .gender(Gender.MALE)
                .birthday(LocalDate.of(1981, 8, 8))
                .yearsOfExperience(32)
                .playerSkill(PlayerSkill.PROFESSIONAL)
                .rankingPoints(2400)
                .matchResults(new PlayerMatchResults(0,0,0))
                .build();
        final Player player3 = Player.builder()
                .firstName("Rafael")
                .lastName("Nadal")
                .gender(Gender.MALE)
                .birthday(LocalDate.of(1982, 4, 21))
                .yearsOfExperience(32)
                .playerSkill(PlayerSkill.PROFESSIONAL)
                .rankingPoints(2400)
                .matchResults(new PlayerMatchResults(0,0,0))
                .build();
        final Player player4 = Player.builder()
                .firstName("Jimmy")
                .lastName("Jimmy")
                .gender(Gender.MALE)
                .birthday(LocalDate.of(1984, 2, 21))
                .yearsOfExperience(12)
                .playerSkill(PlayerSkill.ADVANCED)
                .rankingPoints(2100)
                .matchResults(new PlayerMatchResults(0,0,0))
                .build();
        final Player player5 = Player.builder()
                .firstName("Sarah")
                .lastName("Sarah")
                .gender(Gender.FEMALE)
                .birthday(LocalDate.of(1992, 6, 21))
                .yearsOfExperience(32)
                .playerSkill(PlayerSkill.ADVANCED)
                .rankingPoints(2100)
                .matchResults(new PlayerMatchResults(0,0,0))
                .build();
        final Player player6 = Player.builder()
                .firstName("Jacek")
                .lastName("Jones")
                .gender(Gender.MALE)
                .birthday(LocalDate.of(2002, 4, 21))
                .yearsOfExperience(1)
                .playerSkill(PlayerSkill.BEGINNER)
                .rankingPoints(1500)
                .matchResults(new PlayerMatchResults(0,0,0))
                .build();
        playerRepository.saveAll(List.of(player1,player2,player3,player4,player5,player6));

        final User user1 = User.builder()
                .email("user@user.pl")
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode("pass"))
                .userStatus(UserStatus.ACTIVE)
                .player(player1)
                .build();
        final User user2 = User.builder()
                .email("roger@user.pl")
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode("pass"))
                .player(player2)
                .userStatus(UserStatus.ACTIVE)
                .build();
        final User user3 = User.builder()
                .email("nadal@user.pl")
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode("pass"))
                .player(player3)
                .userStatus(UserStatus.ACTIVE)
                .build();
        final User user4 = User.builder()
                .email("jimmy@user.pl")
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode("pass"))
                .player(player4)
                .userStatus(UserStatus.ACTIVE)
                .build();
        final User user5 = User.builder()
                .email("sarah@user.pl")
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode("pass"))
                .player(player5)
                .userStatus(UserStatus.ACTIVE)
                .build();
        final User user6 = User.builder()
                .email("jacek@user.pl")
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode("pass"))
                .player(player6)
                .userStatus(UserStatus.ACTIVE)
                .build();
        userRepository.saveAll(List.of(user1, user2, user3, user4, user5, user6));
    }
}
