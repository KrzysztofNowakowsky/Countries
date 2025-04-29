package com.example.countrymodel.controller;

import com.example.countrymodel.model.CountryInfo;
import com.example.countrymodel.service.CountryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CountryController.class) // <-- your controller class here
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Test
    void testGetCountryInfo() throws Exception {
        // Arrange
        CountryInfo mockCountry = new CountryInfo();
        mockCountry.setName("Poland");
        mockCountry.setCapital("Warsaw");
        mockCountry.setPopulation(38000000);
        mockCountry.setRegion("Europe");

        when(countryService.getCountry("Poland")).thenReturn(mockCountry);

        // Act + Assert
        mockMvc.perform(get("/api/country/Poland"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country.name").value("Poland"))
                .andExpect(jsonPath("$.country.capital").value("Warsaw"))
                .andExpect(jsonPath("$.country.population").value(38000000))
                .andExpect(jsonPath("$.country.region").value("Europe"));
    }
}
