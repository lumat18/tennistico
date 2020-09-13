package com.gruzini.tennistico.services.entity_related;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.exceptions.CourtNotFoundException;
import com.gruzini.tennistico.models.forms.CourtForm;
import com.gruzini.tennistico.repositories.CourtRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

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
        final Long courtId = getExistingCourtId(court);
        if(courtId == null){
            return courtRepository.save(court);
        }
        return getById(courtId);
    }

    private Long getExistingCourtId(final Court court) {
        if(court.getName() != null){
            if(courtRepository.getFirstByStreetAndCityAndCountryAndName(court.getStreet(), court.getCity(), court.getCountry(), court.getName()).isPresent()){
                return courtRepository.getFirstByStreetAndCityAndCountryAndName(court.getStreet(), court.getCity(), court.getCountry(), court.getName()).get().getId();
            }
        }
        if(court.getHouseNumber() != null){
            if(courtRepository.getFirstByStreetAndCityAndCountryAndHouseNumber(court.getStreet(), court.getCity(), court.getCountry(), court.getHouseNumber()).isPresent()){
                return courtRepository.getFirstByStreetAndCityAndCountryAndHouseNumber(court.getStreet(), court.getCity(), court.getCountry(), court.getHouseNumber()).get().getId();
            }
        }
        if(courtRepository.getFirstByStreetAndCityAndCountry(court.getStreet(), court.getCity(), court.getCountry()).isPresent()){
            return courtRepository.getFirstByStreetAndCityAndCountry(court.getStreet(), court.getCity(), court.getCountry()).get().getId();
        }
        return null;
    }
}
