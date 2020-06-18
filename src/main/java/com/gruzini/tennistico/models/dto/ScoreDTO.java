package com.gruzini.tennistico.models.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@Validated
public class ScoreDTO {
    @NotEmpty
    private List<SetDTO> setDtoList;

    //Validation of number of sets
    //One player can have max 3 sets won - the match is finished then
    @AssertTrue(message = "Invalid score")
    public boolean isScoreValid() {

        final List<SetDTO> hostSetDTOS = setDtoList.stream()
                .filter(setDTO -> setDTO.getHostScore() > setDTO.getGuestScore())
                .collect(Collectors.toList());

        final List<SetDTO> guestSetDTOS = setDtoList.stream()
                .filter(setDTO -> setDTO.getHostScore() < setDTO.getGuestScore())
                .collect(Collectors.toList());

        return hostSetDTOS.size() <= 3 && guestSetDTOS.size() <= 3;
    }
}
