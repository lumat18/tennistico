package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.models.dto.CourtDto;
import com.gruzini.tennistico.models.forms.CourtForm;
import org.springframework.stereotype.Component;

@Component
public class CourtMapper {
    public CourtDto toCourtInfoDto (final Court court){
        return CourtDto.builder()
                .courtId(court.getId())
                .name(court.getName())
                .street(court.getStreet())
                .city(court.getCity())
                .phoneNumber(court.getPhoneNumber())
                .build();
    }

  public Court courtFormToCourt(final CourtForm courtForm) {
    return Court.builder()
            .name(courtForm.getName())
            .email(courtForm.getEmail())
            .phoneNumber(courtForm.getPhoneNumber())
            .website(courtForm.getWebsite())
            .houseNumber(courtForm.getHouseNumber())
            .street(courtForm.getStreet())
            .city(courtForm.getCity())
            .state(courtForm.getState())
            .country(courtForm.getCountry())
            .postcode(courtForm.getPostcode())
            .build();
  }
}
