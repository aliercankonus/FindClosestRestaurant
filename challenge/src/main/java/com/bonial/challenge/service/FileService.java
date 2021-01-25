package com.bonial.challenge.service;

import com.bonial.challenge.exception.InvalidFileContentException;
import com.bonial.challenge.exception.NonExistentFileException;
import com.bonial.challenge.model.Locations;
import com.bonial.challenge.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@Service
@AllArgsConstructor
public class FileService {

    private final ObjectMapper objectMapper;

    public Locations readFromFile(String fileName) {
        try {
            File file = ResourceUtils.getFile(fileName);
            return objectMapper.readValue(file, Locations.class);
        } catch (FileNotFoundException e) {
            log.error(Constants.FILE_NOT_FOUND_MESSAGE, e);
            throw new NonExistentFileException(Constants.FILE_NOT_FOUND_MESSAGE);
        } catch (IOException e) {
            log.error(Constants.INVALID_LOCATION_MESSAGE, e);
            throw new InvalidFileContentException(Constants.INVALID_LOCATION_MESSAGE);
        }
    }
}
