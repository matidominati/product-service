package com.matidominati.productservie.productservice.exception;

import org.springframework.http.HttpStatus;

public class ConfigurationAlreadyExistsException extends ProductException {
    public ConfigurationAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
