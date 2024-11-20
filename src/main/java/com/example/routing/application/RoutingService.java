package com.example.routing.application;

import com.example.routing.domain.model.Country;
import com.example.routing.domain.port.RouteFinderPort;
import com.example.routing.domain.service.RouteFinder;
import com.example.routing.infrastructure.adapter.CountryDataAdapter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoutingService implements InitializingBean {
    private final CountryDataAdapter countryDataAdapter;
    private RouteFinderPort routeFinder;

    public RoutingService(CountryDataAdapter countryDataAdapter) {
        this.countryDataAdapter = countryDataAdapter;
    }

    @Override
    public void afterPropertiesSet() {
        initialize();
    }

    public void initialize() {
        List<Country> countries = countryDataAdapter.loadCountries();
        Map<String, List<String>> graph = new HashMap<>();
        countries.forEach(country -> graph.put(country.getCca3(), country.getBorders()));
        this.routeFinder = new RouteFinder(graph);
    }

    public List<String> getRoute(String origin, String destination) {
        return routeFinder.findRoute(origin, destination);
    }

}