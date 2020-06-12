package com.gruzini.tennistico.bootstrap;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.domain.enums.Gender;
import com.gruzini.tennistico.domain.enums.PlayerSkill;
import com.gruzini.tennistico.domain.enums.UserStatus;
import com.gruzini.tennistico.repositories.CourtRepository;
import com.gruzini.tennistico.repositories.GameRepository;
import com.gruzini.tennistico.repositories.PlayerRepository;
import com.gruzini.tennistico.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class JoinGameInitializer implements CommandLineRunner {
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;
    private final CourtRepository courtRepository;
    private final GameRepository gameRepository;
    private final PasswordEncoder passwordEncoder;

    public JoinGameInitializer(PlayerRepository playerRepository, UserRepository userRepository, CourtRepository courtRepository, GameRepository gameRepository, PasswordEncoder passwordEncoder) {
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
        this.courtRepository = courtRepository;
        this.gameRepository = gameRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        final Court court = Court.builder()
                .name("Krzysztof")
                .city("Madrid")
                .country("Spain")
                .state("Castilla")
                .street("Calle")
                .zipCode("00-000")
                .build();
        courtRepository.save(court);

        final Game game1 = Game.builder()
                .court(court)
                .startingAt(LocalDateTime.of(2020, 6, 12, 8, 30))
                .endingAt(LocalDateTime.of(2020, 6, 12, 11, 30))
                .gameStatus(GameStatus.HOSTED)
                .isOpen(true)
                .build();
        gameRepository.save(game1);

        final Player host = Player.builder()
                .firstName("Roger")
                .lastName("Federer")
                .birthday(LocalDate.of(1981, 8, 8))
                .gender(Gender.MALE)
                .playerSkill(PlayerSkill.PROFESSIONAL)
                .yearsOfExperience(32)
                .games(List.of(game1))
                .build();
        playerRepository.save(host);

        final User user = User.builder()
                .email("user@user.pl")
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode("pass"))
                .player(host)
                .userStatus(UserStatus.ACTIVE)
                .build();
        userRepository.save(user);
    }
}
