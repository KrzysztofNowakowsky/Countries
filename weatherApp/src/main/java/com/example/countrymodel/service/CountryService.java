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
        var response = restTemplate.getForObject(url, Object[].class);
        if (response != null && response.length > 0 && response[0] instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> countryData = (Map<String, Object>) response[0];

            Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");
            List<String> capitals = (List<String>) countryData.get("capital");

            CountryInfo country = new CountryInfo();
            country.setName((String) nameData.get("common"));
            country.setCapital(capitals.get(0));
            country.setPopulation(((Number) countryData.get("population")).longValue());
            country.setRegion((String) countryData.get("region"));

            return country;
        }
        return null;
    }
}