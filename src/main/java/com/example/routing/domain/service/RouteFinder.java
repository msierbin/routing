package com.example.routing.domain.service;

import com.example.routing.domain.port.RouteFinderPort;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

@Profile("!cacheable")
public class RouteFinder implements RouteFinderPort {
    private final Map<String, List<String>> graph;

    public RouteFinder(Map<String, List<String>> graph) {
        this.graph = graph;
    }

    public List<String> findRoute(String origin, String destination) {
        if (!graph.containsKey(origin) || !graph.containsKey(destination)) {
            return Collections.emptyList();
        }

        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(Collections.singletonList(origin));
        visited.add(origin);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String current = path.get(path.size() - 1);

            if (current.equals(destination)) {
                return path;
            }

            for (String neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        return Collections.emptyList();
    }
}