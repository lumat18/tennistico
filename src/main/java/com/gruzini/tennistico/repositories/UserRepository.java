package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.ActivationToken;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM users u JOIN activation_tokens t ON u.id = t.user.id WHERE t.id = :token")
    User findUserByToken(final ActivationToken token);

    Optional<User> findByEmail(String email);

    Optional<User> findByPlayer(Player player);
}
