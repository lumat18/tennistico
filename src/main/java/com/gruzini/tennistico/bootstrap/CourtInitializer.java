package com.gruzini.tennistico.bootstrap;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.repositories.CourtRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Profile("dev")
@Order(2)
public class CourtInitializer implements CommandLineRunner {

    private final CourtRepository courtRepository;

    public CourtInitializer(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        final Court court1 = Court.builder()
                .name("Krzysztof")
                .city("Madrid")
                .country("Spain")
                .state("Castilla")
                .street("Calle")
                .phoneNumber("+00 700 777 777")
                .postCode("00-000")
                .build();
        final Court court2 = Court.builder()
                .phoneNumber("+33 555 222 111")
                .postCode("55-555")
                .street("Key Biscayne")
                .country("Florida")
                .state("Florida")
                .city("Miami")
                .name("Crandon Park Tennis Center")
                .build();
        final Court court3 = Court.builder()
                .phoneNumber("+351 54 781 781")
                .postCode("31-300")
                .street("Rua do Benformoso")
                .state("Portugal")
                .country("Portugal")
                .city("Lisbon")
                .name("Sport Clube Intendente")
                .build();
        final Court court4 = Court.builder()
                .phoneNumber("+00 500 500")
                .postCode("30-30")
                .street("Long")
                .country("Florida")
                .state("Florida")
                .city("Miami")
                .name("Sunny Court Center")
                .build();
        courtRepository.saveAll(List.of(court1, court2, court3, court4));
    }
}
