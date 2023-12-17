package com.weatherforecastapp.weatherforecast.dto;

import java.time.LocalDate;

public class WeatherForecastDto {
    private LocalDate date;
    private int tempMin;
    private int tempMax;
    private String mainWeather;
    private double rainProbability;

    public WeatherForecastDto() {
    }

    public WeatherForecastDto(LocalDate date, int tempMin, int tempMax, String mainWeather, double rainProbability) {
        this.date = date;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.mainWeather = mainWeather;
        this.rainProbability = rainProbability;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public String getMainWeather() {
        return mainWeather;
    }

    public void setMainWeather(String mainWeather) {
        this.mainWeather = mainWeather;
    }

    public double getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(double rainProbability) {
        this.rainProbability = rainProbability;
    }
}
