package com.weatherforecastapp.weatherforecast.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CityExceptionDto cityException(CityException e) {
        return new CityExceptionDto(e);
    }
}