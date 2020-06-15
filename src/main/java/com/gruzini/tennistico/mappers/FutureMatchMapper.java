package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.models.dto.FutureMatchDto;
import org.springframework.stereotype.Component;

@Component
public class FutureMatchMapper {

   public FutureMatchDto toFutureMatchDto(final Match match) {
      return FutureMatchDto.builder()
              .hostName(match.getHost().getFirstName() + " " + match.getHost().getLastName())
              .guestName(prepareGuestName(match))
              .court(match.getCourt().getName() + ", " + match.getCourt().getCity())
              .start(match.getStartingAt())
              .end(match.getEndingAt())
              .build();
   }

   private String prepareGuestName(final Match match){
      if(match.getGuest().isEmpty()){
         return "";
      }
      return match.getGuest().get().getFirstName() + " " + match.getGuest().get().getLastName();
   }
}
