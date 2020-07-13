package com.gruzini.tennistico.services.point_counter;

import org.springframework.stereotype.Component;

@Component(value = "elo")
public class EloRankingPointCounter implements RankingPointCounter{
   private static final Integer K_FACTOR = 32;

   @Override
   public int calculateWinPoints(Integer winnerRanking, Integer loserRanking) {
      final Double E1 = calculateExpectedRanking(winnerRanking, loserRanking);
      final Integer S1E1 = (int) Math.round(K_FACTOR*(1D - E1));
      return winnerRanking + S1E1;
   }

   @Override
   public int calculateDrawPoints(Integer player1Ranking, Integer player2Ranking) {
      final Double E1 = calculateExpectedRanking(player1Ranking, player2Ranking);
      final Integer S1E1 = (int) Math.round(K_FACTOR*(0.5 - E1));
      return player1Ranking + S1E1;
   }

   @Override
   public int calculateLossPoints(Integer loserRanking, Integer winnerRanking) {
      final Double E1 = calculateExpectedRanking(loserRanking, winnerRanking);
      final Integer S1E1 = (int) Math.round(K_FACTOR*(0D - E1));
      return loserRanking + S1E1;
   }

   private Double calculateExpectedRanking(final Integer ranking1, final Integer ranking2){
      final Long R1 = calculateTransformedRanking(ranking1);
      final Long R2 = calculateTransformedRanking(ranking2);
      return (double) R1/(R1 + R2);
   }

   private Long calculateTransformedRanking(final Integer ranking){
      double exponent = ranking / 400;
      return Math.round(Math.pow(10, exponent));
   }
}
