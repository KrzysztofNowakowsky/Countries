package com.example.countrymodel.service;

import com.example.countrymodel.model.CountryInfo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CountryServiceMockTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private CountryService countryService;

    @Test
    void getCountryByName_returnsMappedCountry() {
        String mockName = "Poland";

        // Mock response structure returned by the API
        Object[] mockApiResponse = new Object[] {
                Map.of(
                        "name", Map.of("common", mockName),
                        "capital", List.of("Warsaw"),
                        "population", 38000000,
                        "region", "Europe",
                        "coatOfArms", Map.of() // use an empty map instead of null
                )
        };

        when(restTemplate.getForObject(
                "https://restcountries.com/v3.1/name/" + mockName,
                Object[].class
        )).thenReturn(mockApiResponse);

        CountryInfo result = countryService.getCountry(mockName);

        assertNotNull(result);
        assertEquals("Poland", result.getName());
        assertEquals("Warsaw", result.getCapital());
        assertEquals("Europe", result.getRegion());
    }
    @Test
    void getCountry_handlesEmptyApiResponse() {
        String countryName = "NowhereLand";

        // Simulate empty response from API
        when(restTemplate.getForObject(
                "https://restcountries.com/v3.1/name/" + countryName,
                Object[].class
        )).thenReturn(new Object[0]);

        // You can decide how your service should respond here (null, exception, etc.)
        CountryInfo result = countryService.getCountry(countryName);

        assertNull(result, "Expected null when API returns empty array");
    }
}