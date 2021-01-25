package com.bonial.challenge.controller;

import com.bonial.challenge.resource.LocationResource;
import com.bonial.challenge.resource.RestaurantResource;
import com.bonial.challenge.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/locations")
public class LocationController {
    private final LocationService locationService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationResource getRestaurant(@PathVariable String id) {
        return locationService.getRestaurant(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<RestaurantResource> searchRestaurant(@RequestParam int x, @RequestParam int y) {
        return locationService.searchRestaurant(x, y);
    }
}
