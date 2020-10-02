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
        if(court.getName() != null && !court.getName().equals("")){
            return courtRepository.getFirstByStreetAndCityAndCountryAndName(court.getStreet(), court.getCity(), court.getCountry(), court.getName());
        }
        if(court.getHouseNumber() != null && !court.getHouseNumber().equals("")){
            return courtRepository.getFirstByStreetAndCityAndCountryAndHouseNumber(court.getStreet(), court.getCity(), court.getCountry(), court.getHouseNumber());
        }
        return courtRepository.getFirstByStreetAndCityAndCountry(court.getStreet(), court.getCity(), court.getCountry());
    }
}
