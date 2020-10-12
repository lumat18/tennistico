package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Court;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourtRepository extends JpaRepository<Court, Long> {
    Optional<Court> getFirstByStreetAndCityAndCountryAndHouseNumberAndName(String street, String city, String country, String houseNumber, String name);
}
