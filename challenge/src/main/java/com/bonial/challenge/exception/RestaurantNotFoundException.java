package com.bonial.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RestaurantNotFoundException extends GlobalResponseException {
    public RestaurantNotFoundException(final String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }
}
