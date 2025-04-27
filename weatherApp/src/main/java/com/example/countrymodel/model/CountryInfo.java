package com.example.countrymodel.model;

import lombok.Data;

@Data
public class CountryInfo {
    private String name;
    private String capital;
    private String region;
    private long population;
    private String coatOfArms;
}