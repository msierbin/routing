package com.example.routing.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    private String cca3;
    private List<String> borders;

    public String getCca3() {
        return cca3;
    }

    public List<String> getBorders() {
        return borders;
    }
}