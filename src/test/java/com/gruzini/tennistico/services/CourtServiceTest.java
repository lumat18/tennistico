package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.exceptions.CourtNotFoundException;
import com.gruzini.tennistico.mappers.CourtInfoMapper;
import com.gruzini.tennistico.models.dto.CourtInfoDto;
import com.gruzini.tennistico.repositories.CourtRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@SpringBootTest
class CourtServiceTest {
    private static final String EXISTING_CITY = "city";
    private static final String NON_EXISTING_CITY = "no_city";
    private static final Long EXISTING_ID = 1L;

    @MockBean
    private CourtRepository courtRepository;
    @MockBean
    private CourtInfoMapper courtInfoMapper;

    @Autowired
    private CourtService courtService;

    @Test
    void shouldfindCourtById(){
        Court court = new Court();
        //given
        when(courtRepository.findById(EXISTING_ID)).thenReturn(Optional.of(court));
        //when
        final Court result = courtService.getById(EXISTING_ID);
        //then
        assertThat(result).isNotNull();
    }
    @Test
    void shouldThrowIfIdDoesNotExist(){
        //given
        when(courtRepository.findById(EXISTING_ID)).thenReturn(Optional.empty());
        //when
        //then
        assertThatExceptionOfType(CourtNotFoundException.class).isThrownBy(()->courtService.getById(EXISTING_ID));    }

    @Test
    void shouldfindCourtByCity() {
        //given
        Court court = Court.builder().city(EXISTING_CITY).build();
        CourtInfoDto courtInfoDto = CourtInfoDto.builder().city(EXISTING_CITY).build();
        when(courtRepository.getByCity(EXISTING_CITY)).thenReturn(List.of(court));
        when(courtInfoMapper.toCourtInfoDto(court)).thenReturn(courtInfoDto);
        //when
        final List<CourtInfoDto> result = courtService.getByCity(EXISTING_CITY);
        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getCity()).isEqualTo(EXISTING_CITY);
    }

    @Test
    void shouldNotfindCourtByCity() {
        //given
        Court court = Court.builder().city(EXISTING_CITY).build();
        CourtInfoDto courtInfoDto = CourtInfoDto.builder().city(EXISTING_CITY).build();
        when(courtRepository.getByCity(EXISTING_CITY)).thenReturn(List.of(court));
        when(courtInfoMapper.toCourtInfoDto(court)).thenReturn(courtInfoDto);
        //when
        final List<CourtInfoDto> result = courtService.getByCity(NON_EXISTING_CITY);
        //then
        assertThat(result.size()).isEqualTo(0);
    }

}