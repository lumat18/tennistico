package com.gruzini.tennistico.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArchivedMatchDto {
   private String opponentName;
   private String score;
   private String courtName;
   private LocalDate date;
}
