package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.Score;
import com.gruzini.tennistico.domain.Set;
import com.gruzini.tennistico.models.dto.ScoreDto;
import com.gruzini.tennistico.models.dto.SetDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class ScoreMapper {

   private final SetMapper setMapper;

   public ScoreMapper(SetMapper setMapper) {
      this.setMapper = setMapper;
   }

   public Score toScore(final ScoreDto scoreDTO) {
      return Score.builder()
              .sets(mapSets(prepareSetDtoList(scoreDTO.getSetDtoList())))
              .winner(setWinner(scoreDTO))
              .loser(setLoser(scoreDTO))
              .build();
   }

   private List<SetDto> prepareSetDtoList(final List<SetDto> setDtoList) {
      List<SetDto> preparedSetDtoList = new ArrayList<>();
      setDtoList.forEach(setDto -> {
                 if (!isNull(setDto.getHostScore()) && !isNull(setDto.getGuestScore())) {
                    preparedSetDtoList.add(setDto);
                 }
              });
      return preparedSetDtoList;
   }

   //TODO: implement windDecider here
   private Player setLoser(ScoreDto scoreDTO) {
      return null;
   }

   //TODO: implement windDecider here
   private Player setWinner(ScoreDto scoreDTO) {
      return null;
   }

   private List<Set> mapSets(List<SetDto> setDtoList) {
      return setDtoList.stream()
              .map(setMapper::toSet)
              .collect(Collectors.toList());
   }
}
