package com.apple.assignment.service;

import com.apple.assignment.exception.WeatherForcastBusinessException;
import com.apple.assignment.model.WeatherForecastDto;


public interface WeatherService {
    public WeatherForecastDto getWeatherForcast(String zipcode) throws WeatherForcastBusinessException;
}
