package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    @Query("SELECT sc FROM scores sc JOIN FETCH sc.sets WHERE sc.id =:id")
    Score getWithSets(@Param("id") Long id);
}
