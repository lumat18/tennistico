package com.gruzini.tennistico.services.entity_related;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.exceptions.CourtNotFoundException;
import com.gruzini.tennistico.repositories.CourtRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    public Court getCourt(final Court court) {
        final Optional<Court> existingCourt = getExistingCourt(court);
        return existingCourt.orElseGet(() -> courtRepository.save(court));
    }

    private Optional<Court> getExistingCourt(final Court court) {
        return courtRepository.getFirstByStreetAndCityAndCountryAndHouseNumberAndName(
                court.getStreet(), court.getCity(), court.getCountry(), court.getHouseNumber(), court.getName());
    }
}
