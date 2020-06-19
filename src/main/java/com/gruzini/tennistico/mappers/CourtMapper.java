package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.models.dto.CourtDto;
import org.springframework.stereotype.Component;

@Component
public class CourtMapper {
    public CourtDto toCourtInfoDto (final Court court){
        return CourtDto.builder()
                .courtId(court.getId())
                .name(court.getName())
                .address(court.getStreet() + ", " + court.getState())
                .city(court.getCity())
                .phoneNumber(court.getPhoneNumber())
                .build();
    }
}
