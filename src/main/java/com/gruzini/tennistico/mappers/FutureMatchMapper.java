package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.models.dto.FutureMatchDto;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class FutureMatchMapper {

   public FutureMatchDto toFutureMatchDto(final Match match) {
      return FutureMatchDto.builder()
              .hostName(match.getHost().getFirstName() + " " + match.getHost().getLastName())
              .guestName(prepareGuestName(match))
              .court(match.getCourt().getName() + ", " + match.getCourt().getCity())
              .start(match.getStartingAt())
              .end(match.getEndingAt())
              .matchStatus(match.getMatchStatus().toString())
              .build();
   }

   private String prepareGuestName(final Match match){
      if (match.getPlayers().size() < 2 || isNull(match.getGuest())) {
         return "";
      }
      return match.getGuest().getFirstName() + " " + match.getGuest().getLastName();
   }
}
