package com.example.countrymodel.controller;

import com.example.countrymodel.model.CountryInfo;
import com.example.countrymodel.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService cs) {
        this.countryService = cs;
    }

    @GetMapping("/country/{name}")
    public Map<String, Object> getCountry(@PathVariable String name) {
        CountryInfo country = countryService.getCountry(name);

        Map<String, Object> result = new HashMap<>();
        result.put("country", country);
        return result;
    }
}