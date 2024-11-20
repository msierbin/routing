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
import java.util.concurrent.ConcurrentHashMap;

@Profile("cacheable")
public class RouteFinderCacheable implements RouteFinderPort {
    private final Map<String, List<String>> graph;
    private final Map<String, Map<String, List<String>>> routeCache;

    public RouteFinderCacheable(Map<String, List<String>> graph) {
        this.graph = graph;
        this.routeCache = new ConcurrentHashMap<>();
    }

    @Override
    public List<String> findRoute(String origin, String destination) {
        if (!graph.containsKey(origin) || !graph.containsKey(destination)) {
            return Collections.emptyList();
        }

        // Check cache for precomputed route
        if (routeCache.containsKey(origin) && routeCache.get(origin).containsKey(destination)) {
            return routeCache.get(origin).get(destination);
        }

        // Perform BFS to compute the route
        List<String> route = bfs(origin, destination);

        // Cache the computed route (both directions for symmetry)
        routeCache.computeIfAbsent(origin, k -> new ConcurrentHashMap<>()).put(destination, route);
        routeCache.computeIfAbsent(destination, k -> new ConcurrentHashMap<>()).put(origin, new ArrayList<>(route));
        Collections.reverse(routeCache.get(destination).get(origin));

        return route;
    }

    private List<String> bfs(String origin, String destination) {
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