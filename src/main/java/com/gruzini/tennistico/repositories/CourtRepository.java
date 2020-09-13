package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourtRepository extends JpaRepository<Court, Long> {
    Optional<Court> getFirstByStreetAndCityAndCountry(String street, String city, String country);
    Optional<Court> getFirstByStreetAndCityAndCountryAndHouseNumber(String street, String city, String country, String houseNumber);
    Optional<Court> getFirstByStreetAndCityAndCountryAndName(String street, String city, String country, String name);
}
