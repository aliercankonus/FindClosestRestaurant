package com.bonial.challenge.service;

import com.bonial.challenge.exception.RestaurantNotFoundException;
import com.bonial.challenge.model.Coordinate;
import com.bonial.challenge.model.Location;
import com.bonial.challenge.model.Locations;
import com.bonial.challenge.resource.LocationResource;
import com.bonial.challenge.resource.RestaurantResource;
import com.bonial.challenge.util.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class LocationService {
    private final FileService fileService;
    private final ConverterService converterService;
    private Map<String, Double> distanceMap;

    public LocationResource getRestaurant(String id) {
        Locations locationContent = fileService.readFromFile(Constants.FILE_LOCATION);

        return locationContent.getLocations().parallelStream()
                .filter(location -> location.getId().equals(id))
                .map(converterService::convertToLocationResource)
                .findFirst()
                .orElseThrow(() -> new RestaurantNotFoundException(Constants.RESTAURANT_NOT_FOUND_MESSAGE));
    }

    public List<RestaurantResource> searchRestaurant(int x, int y) {
        Locations locationContent = fileService.readFromFile(Constants.FILE_LOCATION);
        Coordinate userCoordinate = new Coordinate(x, y);
        distanceMap = new HashMap<>();

        return locationContent.getLocations().parallelStream()
                .filter(restaurantLocation -> isUserInCircle(userCoordinate, restaurantLocation))
                .map(this::createRestaurantResource)
                .sorted(Comparator.comparing(RestaurantResource::getDistance))
                .collect(Collectors.toList());
    }

    private boolean isUserInCircle(Coordinate userCoordinate, Location restaurantLocation) {
        Double distance = calculateDistance(userCoordinate, restaurantLocation);
        if (distance <= restaurantLocation.getRadius()){
            distanceMap.put(restaurantLocation.getId(), distance);
            return true;
        }
        return false;
    }

    private Double calculateDistance(Coordinate userCoordinate, Location restaurantLocation) {
        Coordinate restaurantCoordinate = getCoordinate(restaurantLocation.getCoordinates());

        double distance = Math.pow(restaurantCoordinate.getX() - userCoordinate.getX(), 2) +
                Math.pow(restaurantCoordinate.getY() - userCoordinate.getY(), 2);
        return Math.sqrt(distance);
    }

    private Coordinate getCoordinate(String coordinate) {
        String[] parts = coordinate.split(",");
        String x = parts[0].trim().substring(2).trim();
        String y = parts[1].trim().substring(2).trim();
        return new Coordinate(Double.parseDouble(x), Double.parseDouble(y));
    }

    private RestaurantResource createRestaurantResource(Location restaurantLocation) {
        return RestaurantResource.builder()
                .id(restaurantLocation.getId())
                .name(restaurantLocation.getName())
                .coordinates(restaurantLocation.getCoordinates())
                .distance(distanceMap.get(restaurantLocation.getId()))
                .build();
    }
}
