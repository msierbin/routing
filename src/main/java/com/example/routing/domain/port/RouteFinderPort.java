package com.example.routing.domain.port;

import java.util.List;

public interface RouteFinderPort {
    List<String> findRoute(String origin, String destination);
}