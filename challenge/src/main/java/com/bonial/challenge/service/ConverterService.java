package com.bonial.challenge.service;

import com.bonial.challenge.model.Location;
import com.bonial.challenge.resource.LocationResource;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {

    public LocationResource convertToLocationResource(Location location) {
        return LocationResource.builder()
                .id(location.getId())
                .coordinates(location.getCoordinates())
                .image(location.getImage())
                .name(location.getName())
                .openingHours(location.getOpeningHours())
                .type(location.getType())
                .build();
    }
}
