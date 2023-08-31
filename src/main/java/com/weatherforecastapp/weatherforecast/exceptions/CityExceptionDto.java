package com.weatherforecastapp.weatherforecast.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CityExceptionDto {
    private String message;
    private String debugMessage;

    public CityExceptionDto(Throwable ex) {
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public CityExceptionDto(CityException e) {
        this.message = e.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
}
