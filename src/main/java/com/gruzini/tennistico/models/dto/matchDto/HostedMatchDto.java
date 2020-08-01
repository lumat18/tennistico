package com.gruzini.tennistico.models.dto.matchDto;

import com.gruzini.tennistico.models.dto.PlayerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HostedMatchDto {
    private Long id;
    private PlayerDto host;
    private String address;
    private LocalDateTime start;
    private LocalDateTime end;
}
