package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.models.dto.CourtInfoDto;
import org.springframework.stereotype.Component;

@Component
public class CourtInfoMapper {
    public CourtInfoDto toCourtInfoDto (final Court court){
        return CourtInfoDto.builder()
                .name(court.getName())
                .address(court.getStreet() + ", " + court.getState())
                .city(court.getCity())
                .phoneNumber(court.getPhoneNumber())
                .build();
    }
}
