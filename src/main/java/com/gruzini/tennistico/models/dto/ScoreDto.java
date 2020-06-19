package com.gruzini.tennistico.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Data
@AllArgsConstructor
@Builder
@Validated
public class ScoreDto {
    @NotEmpty
    private List<SetDto> setDtoList;

    public ScoreDto(){
        List<SetDto> setDtoList = new ArrayList<>();
        for(int num = 0; num < 5; num++){
            setDtoList.add(new SetDto());
        }
        this.setDtoList = setDtoList;
    }

    //Validation of number of sets
    //One player can have max 3 sets won - the match is finished then
    @AssertTrue(message = "Invalid score")
    public boolean isScoreValid() {

        final List<SetDto> hostSetDtos = setDtoList.stream()
                .filter(setDto -> setDto.getHostScore() > setDto.getGuestScore())
                .collect(Collectors.toList());

        final List<SetDto> guestSetDtos = setDtoList.stream()
                .filter(setDto -> setDto.getHostScore() < setDto.getGuestScore())
                .collect(Collectors.toList());

        return hostSetDtos.size() <= 3 && guestSetDtos.size() <= 3;
    }
}
