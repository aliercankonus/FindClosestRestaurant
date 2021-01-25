package com.bonial.challenge.exception.handler;

import com.bonial.challenge.exception.GlobalResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CustomExceptionHandler implements GlobalExceptionHandler {

    @ExceptionHandler(GlobalResponseException.class)
    public ResponseEntity<Object> handleGlobalResponseException(
            GlobalResponseException ex, WebRequest request) {

        return handleException(request, ex.getMessage(), ex.getId());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(
            WebRequest request, IllegalArgumentException ex) {
        return handleException(request, ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(
            WebRequest request, HttpRequestMethodNotSupportedException ex) {
        return handleException(request, ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> handleHttpMediaTypeNotSupportedException(
            WebRequest request, HttpMediaTypeNotSupportedException ex) {
        return handleException(request, ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(WebRequest request, Exception ex) {
        return handleException(
                request, ex.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
