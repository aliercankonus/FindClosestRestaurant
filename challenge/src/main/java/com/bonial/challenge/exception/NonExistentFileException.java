package com.bonial.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NonExistentFileException extends GlobalResponseException {
    public NonExistentFileException(final String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }
}
