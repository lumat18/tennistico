package com.gruzini.tennistico.bootstrap;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.Gender;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.domain.enums.PlayerSkill;
import com.gruzini.tennistico.domain.enums.UserStatus;
import com.gruzini.tennistico.repositories.CourtRepository;
import com.gruzini.tennistico.repositories.MatchRepository;
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
@Order(2)
public class JoinMatchInitializer implements CommandLineRunner {
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;
    private final CourtRepository courtRepository;
    private final MatchRepository matchRepository;
    private final PasswordEncoder passwordEncoder;

    public JoinMatchInitializer(PlayerRepository playerRepository, UserRepository userRepository, CourtRepository courtRepository, MatchRepository matchRepository, PasswordEncoder passwordEncoder) {
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
        this.courtRepository = courtRepository;
        this.matchRepository = matchRepository;
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
                .phoneNumber("+00 700 777 777")
                .zipCode("00-000")
                .build();
        courtRepository.save(court);

        final Match match1 = Match.builder()
                .court(court)
                .startingAt(LocalDateTime.of(2020, 6, 12, 8, 30))
                .endingAt(LocalDateTime.of(2020, 6, 12, 11, 30))
                .matchStatus(MatchStatus.HOSTED)
                .build();
        matchRepository.save(match1);

        final Player host = Player.builder()
                .firstName("Roger")
                .lastName("Federer")
                .birthday(LocalDate.of(1981, 8, 8))
                .gender(Gender.MALE)
                .playerSkill(PlayerSkill.PROFESSIONAL)
                .yearsOfExperience(32)
                .rankingPoints(1600)
                .build();
        playerRepository.save(host);

        final User userHost = User.builder()
                .email("roger@user.pl")
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode("pass"))
                .player(host)
                .userStatus(UserStatus.ACTIVE)
                .build();
        userRepository.save(userHost);

        final Player guest = Player.builder()
                .firstName("Rafael")
                .lastName("Nadal")
                .birthday(LocalDate.of(1982, 4, 21))
                .gender(Gender.MALE)
                .playerSkill(PlayerSkill.PROFESSIONAL)
                .yearsOfExperience(32)
                .rankingPoints(1600)
                .build();
        playerRepository.save(guest);

        final User userGuest = User.builder()
                .email("nadal@user.pl")
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode("pass"))
                .player(guest)
                .userStatus(UserStatus.ACTIVE)
                .build();
        userRepository.save(userGuest);
    }
}
