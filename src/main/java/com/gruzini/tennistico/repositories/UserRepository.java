package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
