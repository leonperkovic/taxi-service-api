package com.wearenotch.taxi.api.utils;

import java.time.Duration;
import java.util.Map;

public class LocationUtils {
    private LocationUtils() {}

    public static Map<String, MapCoordinates> KNOWN_LOCATIONS = Map.of(
            "Street A", new MapCoordinates(1, 1),
            "Street B", new MapCoordinates(5, 7),
            "Street C", new MapCoordinates(3, 12),
            "Street D", new MapCoordinates(-2, -7)
    );

    public static Duration estimateDuration(String origin, String destination) {
        var originCoordinates = getCoordinates(origin);
        var destinationCoordinates = getCoordinates(destination);
        var deltaX = (double) originCoordinates.getX() - destinationCoordinates.getX();
        var deltaY = (double) originCoordinates.getY() - destinationCoordinates.getY();
        var distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return Duration.ofMinutes((long) distance);
    }

    private static MapCoordinates getCoordinates(String location) {
        if (!KNOWN_LOCATIONS.containsKey(location)) throw new IllegalArgumentException("Unknown location " + location);
        return KNOWN_LOCATIONS.get(location);
    }
}
