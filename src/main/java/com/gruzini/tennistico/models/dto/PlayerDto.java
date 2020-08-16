package com.gruzini.tennistico.models.dto;

import com.gruzini.tennistico.domain.PlayerMatchResults;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerDto {
   private Long id;
   private String firstName;
   private String lastName;
   private Integer rankingPoints;
   private Integer age;
   private String gender;
   private String photoUrl;
   private String rankingPosition;
   private PlayerMatchResults matchResults;

   public String getFullName(){
      return firstName + " " + lastName;
   }
   public String getRatio(){
      return matchResults.getWins() + "/" + matchResults.getDraws() + "/" + matchResults.getLoses();
   }
}
