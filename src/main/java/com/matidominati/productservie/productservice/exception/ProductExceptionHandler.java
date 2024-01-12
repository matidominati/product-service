package com.matidominati.productservie.productservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleMedicalException(DataNotFoundException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ErrorResponse.builder()
                .message(ex.getMessage())
                .httpStatus(ex.getHttpStatus())
                .timestamp(ZonedDateTime.now())
                .build());
    }
}
