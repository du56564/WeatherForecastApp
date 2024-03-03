package com.apple.assignment.service;

import com.apple.assignment.cache.WeatherForcastCache;
import com.apple.assignment.dao.WeatherApiDao;
import com.apple.assignment.exception.WeatherForcastBusinessException;
import com.apple.assignment.helper.ZipCodeValidator;
import com.apple.assignment.model.GeoCodeDto;
import com.apple.assignment.model.TemperatureDto;
import com.apple.assignment.model.WeatherForecastDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WeatherServiceImpl implements WeatherService {
    private WeatherForcastCache weatherForcastCache;
    private WeatherApiDao weatherApiDao;

    public WeatherServiceImpl() {
    }

    @Autowired
    public WeatherServiceImpl(WeatherForcastCache weatherForcastCache, WeatherApiDao weatherApiDao) {
        this.weatherForcastCache = weatherForcastCache;
        this.weatherApiDao = weatherApiDao;
    }

    @Override
    public WeatherForecastDto getWeatherForcast(String zipcode) throws WeatherForcastBusinessException {
        if (!ZipCodeValidator.isValidZipCode(zipcode)) {
            throw new WeatherForcastBusinessException("Invalid zipcode.");
        }
        //If Cache miss or cache expired, fetch new forecast
        if (!weatherForcastCache.isPresentInCache(zipcode)) {
            GeoCodeDto geoCodeDto = weatherApiDao.getGeoCode(zipcode);
            TemperatureDto temperatureDto = weatherApiDao.getTemperature(geoCodeDto.getLongitude(), geoCodeDto.getLatitude());
            weatherForcastCache.setForecastCache(zipcode, new WeatherForecastDto(LocalDateTime.now(), temperatureDto, false));
        } else {
            weatherForcastCache.getForecastCache(zipcode).setCached(true);
        }
        return weatherForcastCache.getForecastCache(zipcode);
    }


}
