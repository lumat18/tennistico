package com.gruzini.tennistico.services.entity_related;

import com.gruzini.tennistico.domain.Score;
import com.gruzini.tennistico.repositories.ScoreRepository;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
   private final ScoreRepository scoreRepository;

   public ScoreService(final ScoreRepository scoreRepository) {
      this.scoreRepository = scoreRepository;
   }

   public Score save(final Score score){
      return scoreRepository.save(score);
   }
}
