package com.gruzini.tennistico.domain.enums;

import lombok.Getter;

@Getter
public enum PlayerSkill {
   BEGINNER(1000),
   AMATEUR(1200),
   ADVANCED(1400),
   PROFESSIONAL(1600);

   private final Integer rankingPoints;

   PlayerSkill(final Integer rankingPoints) {
      this.rankingPoints = rankingPoints;
   }
}
