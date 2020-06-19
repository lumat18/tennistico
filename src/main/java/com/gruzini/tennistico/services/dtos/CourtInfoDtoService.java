package com.gruzini.tennistico.services.dtos;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.mappers.CourtInfoMapper;
import com.gruzini.tennistico.models.dto.CourtInfoDto;
import com.gruzini.tennistico.services.entities.CourtService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourtInfoDtoService {
   private final CourtInfoMapper courtInfoMapper;
   private final CourtService courtService;

   public CourtInfoDtoService(final CourtInfoMapper courtInfoMapper, final CourtService courtService) {
      this.courtInfoMapper = courtInfoMapper;
      this.courtService = courtService;
   }

   public CourtInfoDto getCourtInfoById(final Long courtId){
      return mapToCourtInfoDto(courtService.getById(courtId));
   }

   private CourtInfoDto mapToCourtInfoDto(final Court court){
      return courtInfoMapper.toCourtInfoDto(court);
   }

   public List<CourtInfoDto> getAllCourtDto(){
      return courtService.getAll().stream()
              .map(courtInfoMapper::toCourtInfoDto)
              .collect(Collectors.toList());
   }

   public List<String> getCities(){
      return courtService.getCities();
   }

   public List<CourtInfoDto> getCourtInfoDtoByCity(final String city){
      return courtService.getByCity(city).stream()
              .map(courtInfoMapper::toCourtInfoDto)
              .collect(Collectors.toList());
   }
}
