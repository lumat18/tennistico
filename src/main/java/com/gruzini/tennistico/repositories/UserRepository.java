package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.ActivationToken;
import com.gruzini.tennistico.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from users u join activation_tokens t on u.id=t.user.id where t.id= :token")
    User findUserByToken(final ActivationToken token);

    Optional<User> findByEmail(String email);
}
