package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourtRepository extends JpaRepository<Court, Long> {
    @Query("SELECT DISTINCT c.city FROM courts c")
    List<String> getCities();

    List<Court> getByCity(String city);
}
