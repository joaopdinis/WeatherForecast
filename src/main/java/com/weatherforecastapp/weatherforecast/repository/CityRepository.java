package com.weatherforecastapp.weatherforecast.repository;

import com.weatherforecastapp.weatherforecast.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {

    boolean existsByName(String name);

    boolean existsByLatitudeAndLongitude(Double latitude, Double longitude);
}