package com.gruzini.tennistico.models.dto;

import com.gruzini.tennistico.domain.enums.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FutureMatchDto {
   private String hostName;
   private String guestName;
   private String court;
   private LocalDateTime start;
   private LocalDateTime end;
   private String matchStatus;
}
