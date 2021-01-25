package com.bonial.challenge.service;

import com.bonial.challenge.exception.RestaurantNotFoundException;
import com.bonial.challenge.model.Location;
import com.bonial.challenge.model.Locations;
import com.bonial.challenge.resource.LocationResource;
import com.bonial.challenge.resource.RestaurantResource;
import com.bonial.challenge.util.TestConstants;
import com.bonial.challenge.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {
    @InjectMocks
    private LocationService underTest;

    @Mock
    private  FileService fileService;
    @Mock
    private  ConverterService converterService;

    @Test
    public void shouldGetRestaurant(){
        //GIVEN
        Location location1 = TestUtil.createLocation(TestConstants.ANY_ID_1);
        Location location2 = TestUtil.createLocation(TestConstants.ANY_ID_2);
        Locations locations =TestUtil.createLocations(Arrays.asList(location1, location2));

        Mockito.when(fileService.readFromFile(ArgumentMatchers.anyString())).thenReturn(locations);
        Mockito.when(converterService.convertToLocationResource(location1)).thenReturn(TestUtil.createLocationResource(TestConstants.ANY_ID_1));

        //WHEN
        LocationResource result = underTest.getRestaurant(TestConstants.ANY_ID_1);

        //THEN
        Assertions.assertNotNull(result);
        Assertions.assertEquals(location1.getId(), result.getId());
        Assertions.assertEquals(location1.getCoordinates(), result.getCoordinates());
        Assertions.assertEquals(location1.getName(), result.getName());

    }

    @Test
    public void shouldThrowExceptionWhenRestaurantIsNotFound(){
        //GIVEN
        Location location1 = TestUtil.createLocation(TestConstants.ANY_ID_1);
        Location location2 = TestUtil.createLocation(TestConstants.ANY_ID_2);
        Locations locations =TestUtil.createLocations(Arrays.asList(location1, location2));

        Mockito.when(fileService.readFromFile(ArgumentMatchers.anyString())).thenReturn(locations);

        //WHEN
        Assertions.assertThrows(RestaurantNotFoundException.class , () -> underTest.getRestaurant(TestConstants.ANY_ID_3));
    }

    @Test
    public void shouldSearchRestaurantAndReturnOneSuitableRestaurant(){
        //GIVEN
        int x = 2;
        int y = 2;
        Location location1 = TestUtil.createLocation(TestConstants.ANY_ID_1);
        location1.setCoordinates("x=1,y=1");
        location1.setRadius(5);

        Location location2 = TestUtil.createLocation(TestConstants.ANY_ID_2);
        location2.setCoordinates("x=101,y=101");
        location2.setRadius(1);

        Locations locations =TestUtil.createLocations(Arrays.asList(location1, location2));
        Mockito.when(fileService.readFromFile(ArgumentMatchers.anyString())).thenReturn(locations);

        //WHEN
        List<RestaurantResource> result = underTest.searchRestaurant(x,y);

        //THEN
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(location1.getCoordinates(), result.get(0).getCoordinates());
        Assertions.assertEquals(location1.getId(), result.get(0).getId());
    }

    @Test
    public void shouldSearchRestaurantAndReturnNoSuitableRestaurant(){
        //GIVEN
        int x = 2;
        int y = 2;
        Location location1 = TestUtil.createLocation(TestConstants.ANY_ID_1);
        location1.setCoordinates("x=50,y=50");
        location1.setRadius(1);

        Location location2 = TestUtil.createLocation(TestConstants.ANY_ID_2);
        location2.setCoordinates("x=101,y=101");
        location2.setRadius(1);

        Locations locations =TestUtil.createLocations(Arrays.asList(location1, location2));
        Mockito.when(fileService.readFromFile(ArgumentMatchers.anyString())).thenReturn(locations);

        //WHEN
        List<RestaurantResource> result = underTest.searchRestaurant(x,y);

        //THEN
        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result.size());
    }
}
