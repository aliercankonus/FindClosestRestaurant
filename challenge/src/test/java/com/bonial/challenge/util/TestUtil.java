package com.bonial.challenge.util;

import com.bonial.challenge.model.Location;
import com.bonial.challenge.model.Locations;
import com.bonial.challenge.resource.LocationResource;

import java.util.List;

public class TestUtil {


    public static Location createLocation(String id){
        Location location = new Location();
        location.setId(id);
        location.setImage(TestConstants.ANY_IMAGE);
        location.setName(TestConstants.ANY_NAME);
        location.setOpeningHours(TestConstants.ANY_OPENING_HOURS);
        location.setCoordinates(TestConstants.ANY_COORDINATES);
        location.setRadius(TestConstants.ANY_RADIUS);
        location.setType(TestConstants.ANY_TYPE);

        return location;
    }

    public static Locations createLocations(List<Location> locationList){
       Locations locations =  new Locations();
       locations.setLocations(locationList);

       return locations;
    }

    public static LocationResource createLocationResource(String id){
        LocationResource locationResource = new LocationResource();
        locationResource.setId(id);
        locationResource.setImage(TestConstants.ANY_IMAGE);
        locationResource.setName(TestConstants.ANY_NAME);
        locationResource.setOpeningHours(TestConstants.ANY_OPENING_HOURS);
        locationResource.setCoordinates(TestConstants.ANY_COORDINATES);
        locationResource.setType(TestConstants.ANY_TYPE);

        return locationResource;
    }
}
