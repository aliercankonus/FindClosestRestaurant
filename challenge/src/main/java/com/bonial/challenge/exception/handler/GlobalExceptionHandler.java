package com.bonial.challenge.exception.handler;

import com.bonial.challenge.resource.ErrorResource;
import com.bonial.challenge.util.Constants;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public interface GlobalExceptionHandler {
    default ResponseEntity<Object> handleException(
            WebRequest request, String message, int httpStatusCode) {
        String exceptionMessage = Constants.COMMON_EXCEPTION_MESSAGE;
        if (!message.isEmpty()) {
            exceptionMessage = message;
        }
        String correlationId = MDC.get(Constants.CORRELATION_ID_LOG_VAR_NAME);
        ErrorResource error = new ErrorResource(correlationId, exceptionMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus annotation = HttpStatus.valueOf(httpStatusCode);
        return new ResponseEntity<>(error, headers, annotation);
    }
}
