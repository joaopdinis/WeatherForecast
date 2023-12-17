package com.weatherforecastapp.weatherforecast.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherforecastapp.weatherforecast.dto.CityDto;
import com.weatherforecastapp.weatherforecast.dto.WeatherForecastDto;
import com.weatherforecastapp.weatherforecast.exceptions.CityException;
import com.weatherforecastapp.weatherforecast.exceptions.ErrorMessage;
import com.weatherforecastapp.weatherforecast.model.*;
import com.weatherforecastapp.weatherforecast.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class WeatherService {

    private final CityRepository cityRepository;

    @Autowired
    public WeatherService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }



    public String deleteCity(Long id) {
        // Delete a city from the database
        cityRepository.deleteById(id);
        return "success";
    }
    public List<WeatherForecastDto> getCityForecast(Long id) {

        // Try to find the city Id in the database. If not find city id does not exist
        City city = cityRepository.findById(id).orElse(null);
        if (city == null) {
            throw new CityException(ErrorMessage.ID_DOES_NOT_EXIST, id);
        }

        // Get the city forecast for the next 5 days from the openweathermap api
        String apiKey = "1427ab33252ab6a1399d833eaf84e908";
        String url = String.format("https://api.openweathermap.org/data/2.5/forecast?lat=%f&lon=%f&appid=%s&units=metric", city.getLatitude(), city.getLongitude(), apiKey);

        RestTemplate restTemplate = new RestTemplate();

        // Save the response into a costume class
        WeatherForecast response = restTemplate.getForObject(url, WeatherForecast.class);;

        assert response != null;

        // Aggregate the data by date
        Map<LocalDate, List<WeatherData>> map = response.getWeatherDataList().stream()
                .collect(Collectors.groupingBy(WeatherData::getDate));

        // The Data comes with a 3h timestamp. And we want it by day
        // Create a WeatherForecastDto Object where each value derives from a specific calculation

        return  map.entrySet().stream()
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    List<WeatherData> weatherDataList = entry.getValue();

                    WeatherForecastDto dto = new WeatherForecastDto();
                    dto.setDate(date);

                    // Get the Minimum temperature of that day
                    dto.setTempMin((int) weatherDataList.stream()
                            .mapToDouble(data -> data.getWeatherMain().getTempMin())
                            .min()
                            .orElse(0.0));

                    // Get the Maximum temperature of that day
                    dto.setTempMax((int) weatherDataList.stream()
                            .mapToDouble(data -> data.getWeatherMain().getTempMax())
                            .max()
                            .orElse(0.0));

                    // Get a map with each weather type of that day and their respective occurrences
                    // For example: Map = {(Clouds: 1), (Clear: 3)}

                    Map<String, Long> m = weatherDataList.stream()
                            .map(WeatherData::getWeather)
                            .collect(Collectors.groupingBy(WeatherDescription::getWeather, Collectors.counting()));

                    // Get the weather type most frequent
                    dto.setMainWeather(m.entrySet().stream()
                            .max(Map.Entry.comparingByValue())
                            .map(Map.Entry::getKey)
                            .orElse(""));

                    // Get the average probability of precipitation
                    dto.setRainProbability( weatherDataList.stream()
                            .mapToDouble(WeatherData::getRainProbability)
                            .average()
                            .orElse(0.0));
                    return dto;
                })

                // Sort it by date and return it as a list
                .sorted(Comparator.comparing(WeatherForecastDto::getDate))
                .toList();


    }


    public String registerCity(CityDto city) {
        String cityName = city.getCityName();

        // Check if city already exists in database

        if (cityRepository.existsByName(cityName)) {
            throw new CityException(ErrorMessage.CITY_ALREADY_EXISTS, cityName);
        }

        String apiKey = "1427ab33252ab6a1399d833eaf84e908";
        // Get the city coordinates from openweathermap api
        String url = String.format("https://api.openweathermap.org/geo/1.0/direct?q=%s&appid=%s&limit=1", cityName, apiKey);

        RestTemplate restTemplate = new RestTemplate();


        String response = restTemplate.getForObject(url, String.class);


        // Parse the String response into a Json Object and save the coordinates into the database
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);

            // If the response is an empty list then the city does not exist
            if (rootNode.isEmpty()) {
                throw new CityException(ErrorMessage.CITY_NOT_VALID, cityName);
            }

            JsonNode infoNode = rootNode.get(0);

            double latitude = infoNode.path("lat").asDouble();
            double longitude = infoNode.path("lon").asDouble();

            // If we already have a city with that latitude and longitude, the city already exists
            if(cityRepository.existsByLatitudeAndLongitude(latitude, longitude)) {
                throw new CityException(ErrorMessage.CITY_ALREADY_EXISTS, cityName);
            }


            cityRepository.save(new City(cityName, latitude, longitude));

            return "Success";
        } catch (Exception e) {
            throw new CityException(ErrorMessage.CITY_NOT_VALID, cityName);
        }

    }

    public List<City> getCitiesList() {
        // Get all the cities in the database
        return cityRepository.findAll();
    }
}