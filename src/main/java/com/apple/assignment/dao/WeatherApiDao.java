package com.apple.assignment.dao;

import com.apple.assignment.exception.WeatherForcastBusinessException;
import com.apple.assignment.model.GeoCodeDto;
import com.apple.assignment.model.TemperatureDto;

public interface WeatherApiDao {
    public GeoCodeDto getGeoCode(String zipcode) throws WeatherForcastBusinessException;

    public TemperatureDto getTemperature(String longitude, String latitude) throws WeatherForcastBusinessException;
}
