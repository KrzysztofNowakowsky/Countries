package com.example.countrymodel.service;

import com.example.countrymodel.model.CountryInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryService {

    private final RestTemplate restTemplate = new RestTemplate();

    public CountryInfo getCountry(String countryName) {
        String url = "https://restcountries.com/v3.1/name/" + countryName;
        var response = restTemplate.getForObject(url, Object[].class);
        //var countryData = (Map<String, Object>) response[0];
        System.out.println(response);
        CountryInfo info = new CountryInfo();
        return info;
        // info.setName(((Map<String, String>) countryData.get("name")).get("common"));
        // info.setCapital(((List<String>) countryData.get("capital")).get(0));
        // info.setRegion((String) countryData.get("region"));
        // info.setPopulation(((Number) countryData.get("population")).longValue());

        // return info;
    }
}