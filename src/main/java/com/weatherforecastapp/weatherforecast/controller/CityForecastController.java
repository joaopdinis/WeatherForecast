package com.weatherforecastapp.weatherforecast.controller;

import com.weatherforecastapp.weatherforecast.dto.WeatherForecastDto;
import com.weatherforecastapp.weatherforecast.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/city-forecast")
public class CityForecastController {
    private final WeatherService weatherService;
    @Autowired
    public CityForecastController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{id}")
    public List<WeatherForecastDto> getCityForecast(@PathVariable Long id) {
        return this.weatherService.getCityForecast(id);
    }
}
