package com.gruzini.tennistico.domain.enums;

import lombok.Getter;

@Getter
public enum PlayerSkill {
   BEGINNER(1500),
   AMATEUR(1800),
   ADVANCED(2100),
   PROFESSIONAL(2400);

   private final Integer rankingPoints;

   PlayerSkill(final Integer rankingPoints) {
      this.rankingPoints = rankingPoints;
   }
}
