package com.weatherforecastapp.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDescription {
    @JsonProperty("main")
    private String weather;

    @JsonProperty("description")
    private String weatherDescription;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }
}
