package com.bonial.challenge.util;

public class Constants {
    public static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";
    public static final String CORRELATION_ID_LOG_VAR_NAME = "Correlation-Id";
    public static final String COMMON_EXCEPTION_MESSAGE = "Exception occurred while system is running.";
    public static final String FILE_LOCATION = "classpath:locations.json";
    public static final String FILE_NOT_FOUND_MESSAGE = "File cannot be found!";
    public static final String RESTAURANT_NOT_FOUND_MESSAGE = "Restaurant cannot be found!";
    public static final String INVALID_LOCATION_MESSAGE = "While reading file content, exception occured!";


    private Constants() {
    }
}
