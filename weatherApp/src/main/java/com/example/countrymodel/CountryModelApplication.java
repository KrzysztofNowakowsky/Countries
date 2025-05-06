package com.example.countrymodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CountryModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountryModelApplication.class, args);
    }
}