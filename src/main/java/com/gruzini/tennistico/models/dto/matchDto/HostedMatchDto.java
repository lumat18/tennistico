package com.gruzini.tennistico.models.dto.matchDto;

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
    private String hostName;
    private String hostGender;
    private Integer hostAge;
    private String hostLevel;
    private Integer hostExp;
    private String address;
    private LocalDateTime start;
    private LocalDateTime end;
}
