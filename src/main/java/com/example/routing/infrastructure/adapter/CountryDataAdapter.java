package com.example.routing.infrastructure.adapter;

import com.example.routing.domain.model.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class CountryDataAdapter {
    public List<Country> loadCountries() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("countries.json");
            return mapper.readValue(is, new TypeReference<List<Country>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load countries data", e);
        }
    }
}