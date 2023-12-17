package com.weatherforecastapp.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherForecast {
    @JsonProperty("list")
    private List<WeatherData> weatherDataList;

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }

    public void setWeatherDataList(List<WeatherData> weatherDataList) {
        this.weatherDataList = weatherDataList;
    }
}


