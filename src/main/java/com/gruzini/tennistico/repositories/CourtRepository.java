package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Court;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court, Long> {
}
