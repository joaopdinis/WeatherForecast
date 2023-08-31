package com.weatherforecastapp.weatherforecast.exceptions;

public enum ErrorMessage {
    CITY_ALREADY_EXISTS("The city %s already exists"),
    CITY_NOT_VALID("%s is not a valid city"),
    ID_DOES_NOT_EXIST("City Id %d does not exits");

    public final String label;

    ErrorMessage(String label) {
        this.label = label;
    }
}
