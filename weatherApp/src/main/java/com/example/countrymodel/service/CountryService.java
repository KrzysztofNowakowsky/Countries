package com.example.countrymodel.service;

import com.example.countrymodel.model.CountryInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class CountryService {

    private final RestTemplate restTemplate = new RestTemplate();

    public CountryInfo getCountry(String countryName) {
        String url = "https://restcountries.com/v3.1/name/" + countryName;
        try {
            Object[] response = restTemplate.getForObject(url, Object[].class);
            if (response != null && response.length > 0 && response[0] instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> countryData = (Map<String, Object>) response[0];

                String name = (String) ((Map<String, Object>) countryData.get("name")).get("common");
                List<String> capitals = (List<String>) countryData.get("capital");
                String capital = (capitals != null && !capitals.isEmpty()) ? capitals.get(0) : "Unknown";
                long population = ((Number) countryData.getOrDefault("population", 0)).longValue();
                String region = (String) countryData.getOrDefault("region", "Unknown");
                String coatOfArms = (String) ((Map<String, Object>) countryData.get("coatOfArms")).get("png");

                CountryInfo country = new CountryInfo();
                country.setName(name);
                country.setCapital(capital);
                country.setPopulation(population);
                country.setRegion(region);
                country.setCoatOfArms(coatOfArms);

                return country;
            }
        } catch (Exception e) {
            // Log the error and handle it appropriately
            System.err.println("Error fetching country data: " + e.getMessage());
        }
        return null;
    }
}