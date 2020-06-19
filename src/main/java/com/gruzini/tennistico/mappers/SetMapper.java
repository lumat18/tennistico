package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Set;
import com.gruzini.tennistico.models.dto.SetDto;
import org.springframework.stereotype.Component;

@Component
public class SetMapper {
    public Set toSet(final SetDto setDto){
        return Set.builder()
                .guestScore(setDto.getGuestScore())
                .hostScore(setDto.getHostScore())
                .build();
    }
}
