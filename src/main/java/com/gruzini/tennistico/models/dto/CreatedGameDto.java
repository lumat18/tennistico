package com.gruzini.tennistico.models.dto;

import com.gruzini.tennistico.domain.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatedGameDto {

    private GameStatus gameStatus;
    @NotNull
    private Long courtId;
//    @NotNull
    private LocalDate date;
//    @NotNull
    private LocalTime start;
//    @NotNull
    private LocalTime end;

//    @AssertTrue
//    public boolean isStartAndEndingTimeValid(){
//        return start.isBefore(end);
//    }
}
