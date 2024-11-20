package com.example.routing.adapters.inbound.rest;

import com.example.routing.application.RoutingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RoutingController {
    private final RoutingService routingService;

    public RoutingController(RoutingService routingService) {
        this.routingService = routingService;
    }

    @GetMapping("/routing/{origin}/{destination}")
    public ResponseEntity<?> getRoute(@PathVariable String origin, @PathVariable String destination) {
        List<String> route = routingService.getRoute(origin, destination);
        if (route.isEmpty()) {
            return ResponseEntity.badRequest().body("No land route found");
        }
        return ResponseEntity.ok().body(Map.of("route", route));
    }
}