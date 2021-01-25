package com.bonial.challenge.service;

import com.bonial.challenge.exception.InvalidFileContentException;
import com.bonial.challenge.model.Location;
import com.bonial.challenge.model.Locations;
import com.bonial.challenge.util.TestConstants;
import com.bonial.challenge.util.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {
    @InjectMocks
    private FileService underTest;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    public void shouldReadFromFile() throws IOException {
        //GIVEN
        String fileName = "classpath:locations.json";
        Location location1 = TestUtil.createLocation(TestConstants.ANY_ID_1);
        Location location2 = TestUtil.createLocation(TestConstants.ANY_ID_2);
        Locations locations =TestUtil.createLocations(Arrays.asList(location1, location2));

        Mockito.when(objectMapper.readValue(any(File.class),eq(Locations.class))).thenReturn(locations);

        //WHEN
        Locations result = underTest.readFromFile(fileName);

        //THEN
        Assertions.assertNotNull(result);
        Mockito.verify(objectMapper, Mockito.times(1)).readValue(any(File.class),eq(Locations.class));
    }

    @Test
    public void shouldThrowExceptionWhenFileIncludesUnknownFields() throws IOException {
        //GIVEN
        String fileName = "classpath:locations.json";
        Mockito.when(objectMapper.readValue(any(File.class),eq(Locations.class))).thenThrow(IOException.class);

        //WHEN
        Assertions.assertThrows(InvalidFileContentException.class , ()-> underTest.readFromFile(fileName));
    }



}
