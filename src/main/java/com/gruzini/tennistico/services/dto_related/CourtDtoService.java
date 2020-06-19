package com.gruzini.tennistico.services.dto_related;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.mappers.CourtMapper;
import com.gruzini.tennistico.models.dto.CourtDto;
import com.gruzini.tennistico.services.entity_related.CourtService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourtDtoService {
   private final CourtMapper courtMapper;
   private final CourtService courtService;

   public CourtDtoService(final CourtMapper courtMapper, final CourtService courtService) {
      this.courtMapper = courtMapper;
      this.courtService = courtService;
   }

   public CourtDto getCourtInfoById(final Long courtId){
      return mapToCourtInfoDto(courtService.getById(courtId));
   }

   private CourtDto mapToCourtInfoDto(final Court court){
      return courtMapper.toCourtInfoDto(court);
   }

   public List<CourtDto> getAllCourtDto(){
      return courtService.getAll().stream()
              .map(courtMapper::toCourtInfoDto)
              .collect(Collectors.toList());
   }

   public List<String> getCities(){
      return courtService.getCities();
   }

   public List<CourtDto> getCourtInfoDtoByCity(final String city){
      return courtService.getByCity(city).stream()
              .map(courtMapper::toCourtInfoDto)
              .collect(Collectors.toList());
   }
}
