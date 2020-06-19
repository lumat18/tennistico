package com.gruzini.tennistico.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetDto {
   @Min(0)
   private int hostScore;
   @Min(0)
   private int guestScore;

   @AssertTrue
   public boolean isSetValid() {

      //normal scenario
      if (hostScore == 6 && guestScore <= 4) {
         return true;
      }
      if (guestScore == 6 && hostScore <= 4) {
         return true;
      }
      //tiebreak scenario
      if (hostScore == 7 && guestScore == 6 || hostScore == 6 && guestScore == 7) {
         return true;
      }
      //advantage scenario
      return hostScore == 7 && guestScore == 5 || hostScore == 5 && guestScore == 7;
      //other scenarios are invalid

   }
}
