package com.gruzini.tennistico.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;

import static java.util.Objects.isNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetDto {
    private Integer hostScore;
    private Integer guestScore;

    @AssertTrue
    public boolean isSetValid() {
        if(isNull(hostScore) || isNull(guestScore)){
            return true;
        } else {
            if(hostScore < 0 || guestScore < 0){
                return false;
            }
            //normal scenario
            if (hostScore == 6 && guestScore <= 4) {
                return true;
            }
            if (guestScore == 6 && hostScore <= 4) {
                return true;
            }
            //tiebreak scenario
            if (hostScore == 7 && guestScore == 6 || hostScore == 6 && guestScore == 7) {
                return true;
            }
            //advantage scenario
            return hostScore == 7 && guestScore == 5 || hostScore == 5 && guestScore == 7;
            //other scenarios are invalid
        }
    }
}
