package com.gruzini.tennistico.services;

import com.gruzini.tennistico.mappers.CourtInfoMapper;
import com.gruzini.tennistico.models.dto.CourtInfoDto;
import com.gruzini.tennistico.repositories.CourtRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CourtService {
    private final CourtRepository courtRepository;
    private final CourtInfoMapper courtInfoMapper;

    public CourtService(CourtRepository courtRepository, CourtInfoMapper courtInfoMapper) {
        this.courtRepository = courtRepository;
        this.courtInfoMapper = courtInfoMapper;
    }
    public List<CourtInfoDto> getAll(){
        return courtRepository.findAll().stream()
                .map(courtInfoMapper::toCourtInfoDto)
                .collect(Collectors.toList());
    }
}
