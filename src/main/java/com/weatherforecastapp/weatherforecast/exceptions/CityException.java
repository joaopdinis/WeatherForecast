package com.weatherforecastapp.weatherforecast.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class CityException extends RuntimeException{
    private final ErrorMessage errorMessage;

    public CityException(ErrorMessage errorMessage) {
        super(errorMessage.label);
        this.errorMessage = errorMessage;
    }

    public CityException(ErrorMessage errorMessage, String value) {
        super(String.format(errorMessage.label, value));
        this.errorMessage = errorMessage;
    }

    public CityException(ErrorMessage errorMessage, Long value) {
        super(String.format(errorMessage.label, value));
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return this.errorMessage;
    }
}
