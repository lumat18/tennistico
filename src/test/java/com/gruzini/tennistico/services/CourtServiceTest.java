package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.exceptions.CourtNotFoundException;
import com.gruzini.tennistico.mappers.CourtMapper;
import com.gruzini.tennistico.models.dto.CourtDto;
import com.gruzini.tennistico.repositories.CourtRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CourtServiceTest {
//    private static final String EXISTING_CITY = "city";
//    private static final String NON_EXISTING_CITY = "no_city";
//    private static final Long EXISTING_ID = 1L;
//
//    @MockBean
//    private CourtRepository courtRepository;
//    @MockBean
//    private CourtInfoMapper courtInfoMapper;
//
//    @Autowired
//    private CourtService courtService;
//
//    @Test
//    void shouldfindCourtById(){
//        Court court = new Court();
//        //given
//        when(courtRepository.findById(EXISTING_ID)).thenReturn(Optional.of(court));
//        //when
//        final Court result = courtService.getById(EXISTING_ID);
//        //then
//        assertThat(result).isNotNull();
//    }
//    @Test
//    void shouldThrowIfIdDoesNotExist(){
//        //given
//        when(courtRepository.findById(EXISTING_ID)).thenReturn(Optional.empty());
//        //when
//        //then
//        assertThatExceptionOfType(CourtNotFoundException.class).isThrownBy(()->courtService.getById(EXISTING_ID));    }
//
//    @Test
//    void shouldfindCourtByCity() {
//        //given
//        Court court = Court.builder().city(EXISTING_CITY).build();
//        CourtInfoDto courtInfoDto = CourtInfoDto.builder().city(EXISTING_CITY).build();
//        when(courtRepository.getByCity(EXISTING_CITY)).thenReturn(List.of(court));
//        when(courtInfoMapper.toCourtInfoDto(court)).thenReturn(courtInfoDto);
//        //when
//        final List<CourtInfoDto> result = courtService.getByCity(EXISTING_CITY);
//        //then
//        assertThat(result.size()).isEqualTo(1);
//        assertThat(result.get(0).getCity()).isEqualTo(EXISTING_CITY);
//    }
//
//    @Test
//    void shouldNotfindCourtByCity() {
//        //given
//        Court court = Court.builder().city(EXISTING_CITY).build();
//        CourtInfoDto courtInfoDto = CourtInfoDto.builder().city(EXISTING_CITY).build();
//        when(courtRepository.getByCity(EXISTING_CITY)).thenReturn(List.of(court));
//        when(courtInfoMapper.toCourtInfoDto(court)).thenReturn(courtInfoDto);
//        //when
//        final List<CourtInfoDto> result = courtService.getByCity(NON_EXISTING_CITY);
//        //then
//        assertThat(result.size()).isEqualTo(0);
//    }

//    @MockBean
//    private CourtRepository courtRepository;
//    @MockBean
//    private CourtMapper courtMapper;
//
//    @Autowired
//    private CourtService courtService;
//
//    @Test
//    void shouldfindCourtById(){
//        Court court = new Court();
//        //given
//        when(courtRepository.findById(EXISTING_ID)).thenReturn(Optional.of(court));
//        //when
//        final Court result = courtService.getById(EXISTING_ID);
//        //then
//        assertThat(result).isNotNull();
//    }
//    @Test
//    void shouldThrowIfIdDoesNotExist(){
//        //given
//        when(courtRepository.findById(EXISTING_ID)).thenReturn(Optional.empty());
//        //when
//        //then
//        assertThatExceptionOfType(CourtNotFoundException.class).isThrownBy(()->courtService.getById(EXISTING_ID));    }
//
//    @Test
//    void shouldfindCourtByCity() {
//        //given
//        Court court = Court.builder().city(EXISTING_CITY).build();
//        CourtDto courtDto = CourtDto.builder().city(EXISTING_CITY).build();
//        when(courtRepository.getByCity(EXISTING_CITY)).thenReturn(List.of(court));
//        when(courtMapper.toCourtInfoDto(court)).thenReturn(courtDto);
//        //when
//        final List<CourtDto> result = courtService.getByCity(EXISTING_CITY);
//        //then
//        assertThat(result.size()).isEqualTo(1);
//        assertThat(result.get(0).getCity()).isEqualTo(EXISTING_CITY);
//    }
//
//    @Test
//    void shouldNotfindCourtByCity() {
//        //given
//        Court court = Court.builder().city(EXISTING_CITY).build();
//        CourtDto courtDto = CourtDto.builder().city(EXISTING_CITY).build();
//        when(courtRepository.getByCity(EXISTING_CITY)).thenReturn(List.of(court));
//        when(courtMapper.toCourtInfoDto(court)).thenReturn(courtDto);
//        //when
//        final List<CourtDto> result = courtService.getByCity(NON_EXISTING_CITY);
//        //then
//        assertThat(result.size()).isEqualTo(0);
//    }

}