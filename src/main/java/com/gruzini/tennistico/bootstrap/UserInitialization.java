package com.gruzini.tennistico.bootstrap;

import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.Gender;
import com.gruzini.tennistico.domain.enums.PlayerSkill;
import com.gruzini.tennistico.domain.enums.UserStatus;
import com.gruzini.tennistico.messages.NotificationMessages;
import com.gruzini.tennistico.repositories.NotificationRepository;
import com.gruzini.tennistico.repositories.PlayerRepository;
import com.gruzini.tennistico.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Profile("dev")
public class UserInitialization implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final NotificationRepository notificationRepository;

    public UserInitialization(PasswordEncoder passwordEncoder, UserRepository userRepository, PlayerRepository playerRepository, NotificationRepository notificationRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        final Player player = Player.builder()
                .playerSkill(PlayerSkill.AMATEUR)
                .yearsOfExperience(3)
                .gender(Gender.MALE)
                .birthday(LocalDate.of(1970,2,3))
                .firstName("Pete")
                .lastName("Sampras")
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
        final Notification notification1 = Notification.builder()
                .message(NotificationMessages.MATCH_CREATED)
                .recipient(user)
                .localDateTime(LocalDateTime.now())
                .build();
        final Notification notification2 = Notification.builder()
                .message(NotificationMessages.JOIN_REQUEST)
                .recipient(user)
                .localDateTime(LocalDateTime.now())
                .build();
        final Notification notification3 = Notification.builder()
                .message(NotificationMessages.JOIN_CONFIRMED)
                .recipient(user)
                .localDateTime(LocalDateTime.now())
                .build();
        final Notification notification4 = Notification.builder()
                .message(NotificationMessages.SCORE_TO_CONFIRM)
                .recipient(user)
                .localDateTime(LocalDateTime.now())
                .build();
        final Notification notification5 = Notification.builder()
                .message(NotificationMessages.SCORE_TO_CONFIRM)
                .recipient(user)
                .localDateTime(LocalDateTime.now())
                .build();
        notificationRepository.save(notification1);
        notificationRepository.save(notification2);
        notificationRepository.save(notification3);
        notificationRepository.save(notification4);
        notificationRepository.save(notification5);

    }
}
