package com.gruzini.tennistico.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourtDto {
    private Long courtId;
    private String name;
    private String address;
    private String city;
    private String phoneNumber;
}
