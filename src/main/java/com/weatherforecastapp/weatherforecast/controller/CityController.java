package com.weatherforecastapp.weatherforecast.controller;

import com.weatherforecastapp.weatherforecast.dto.CityDto;
import com.weatherforecastapp.weatherforecast.model.City;
import com.weatherforecastapp.weatherforecast.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final WeatherService weatherService;
    @Autowired
    public CityController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/list")
    public List<City> getCities() {
        return this.weatherService.getCitiesList();
    }

    @PostMapping("/register")
    public String registerCity(@RequestBody CityDto city) {
        return this.weatherService.registerCity(city);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCity(@PathVariable Long id) {
        return this.weatherService.deleteCity(id);
    }
}
