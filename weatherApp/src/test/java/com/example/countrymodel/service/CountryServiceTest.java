package com.example.countrymodel.service;

import com.example.countrymodel.model.CountryInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    void getCountryByName_returnsCorrectCountry() {
        CountryInfo country = countryService.getCountry("Poland");

        assertNotNull(country);
        assertEquals("Poland", country.getName());
        assertEquals("Warsaw", country.getCapital());
        assertTrue(country.getPopulation() > 0);
        assertEquals("Europe", country.getRegion());
    }
}