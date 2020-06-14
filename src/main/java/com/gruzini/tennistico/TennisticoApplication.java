package com.gruzini.tennistico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TennisticoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TennisticoApplication.class, args);
    }

}
