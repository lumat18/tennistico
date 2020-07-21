package com.gruzini.tennistico.models.dto.matchDto;

import com.gruzini.tennistico.models.dto.PlayerDto;
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
    private PlayerDto host;
    private PlayerDto guest;
    private String court;
    private LocalDateTime start;
    private LocalDateTime end;
    private String matchStatus;
    private boolean isInvolvedPlayerHost;
}
