package com.apple.assignment.controller;

import com.apple.assignment.constants.CommonConstants;
import com.apple.assignment.model.TemperatureDto;
import com.apple.assignment.model.WeatherForecastResponse;
import com.apple.assignment.model.WeatherForecastDto;
import com.apple.assignment.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/weather/v1")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;


    @GetMapping("/forecast")
    public ResponseEntity<?> getWeatherForcast(@RequestParam String zipcode) throws Exception {
        WeatherForecastResponse weatherForeCastResponse = new WeatherForecastResponse();
        WeatherForecastDto weatherForecastDto = weatherService.getWeatherForcast(zipcode);
        if (weatherForecastDto != null) {
            TemperatureDto temperatureDto = weatherForecastDto.getTemperatureDto();
            LocalDateTime timestamp = weatherForecastDto.getTimestamp();
            weatherForeCastResponse.setCurrentTemperature(temperatureDto.getCurrentTemperature());
            weatherForeCastResponse.setHighTemperature(temperatureDto.getMaxTemperature());
            weatherForeCastResponse.setLowTemperature(temperatureDto.getMinTemperature());
            weatherForeCastResponse.setTemperatureUnit(CommonConstants.DEGREE_CELSIUS);
            weatherForeCastResponse.setTimestamp(timestamp);
            weatherForeCastResponse.setIndicator(weatherForecastDto.isCached() ? CommonConstants.CACHED_TEMPERATURE : CommonConstants.LATEST_TEMPERATURE);
        }
        return new ResponseEntity<>(weatherForeCastResponse, HttpStatus.OK);
    }
}
