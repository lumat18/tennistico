package com.gruzini.tennistico.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerDto {
   private String firstName;
   private String lastName;
   private Integer rankingPoints;
   private Integer age;
   private String gender;
   private String photoUrl;

   public String getFullName(){
      return firstName + " " + lastName;
   }
}
