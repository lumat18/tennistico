package com.gruzini.tennistico.services.entities;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.exceptions.CourtNotFoundException;
import com.gruzini.tennistico.repositories.CourtRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@Transactional
@Slf4j
public class CourtService {
    private final CourtRepository courtRepository;

    public CourtService(final CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }
    public List<Court> getAll(){
        return courtRepository.findAll();
    }

    public Court getById(Long courtId) {
        return courtRepository.findById(courtId).orElseThrow(CourtNotFoundException::new);
    }

    public List<String> getCities() {
        return courtRepository.getCities();
    }

    public List<Court> getByCity(final String city) {
        if (isNull(city)) throw new CourtNotFoundException();
        return courtRepository.getByCity(city);
    }
}
