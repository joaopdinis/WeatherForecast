package com.weatherforecastapp.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WeatherData {
    @JsonProperty("main")
    private WeatherMain weatherMain;
    @JsonProperty("weather")
    private List<WeatherDescription> weather;
    @JsonProperty("pop")
    private double rainProbability;
    @JsonProperty("dt_txt")
    private String dateString;

    public LocalDate getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(this.dateString, formatter);

        return dateTime.toLocalDate();
    }

    public WeatherMain getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(WeatherMain weatherMain) {
        this.weatherMain = weatherMain;
    }

    public WeatherDescription getWeather() {
        return this.weather.stream()
                .findFirst()
                .orElse(null);
    }

    public void setWeather(List<WeatherDescription> weather) {
        this.weather = weather;
    }

    public double getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(double rainProbability) {
        this.rainProbability = rainProbability;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }


}
