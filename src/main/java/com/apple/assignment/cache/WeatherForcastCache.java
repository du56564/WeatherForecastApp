package com.apple.assignment.cache;

import com.apple.assignment.model.WeatherForecastDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class WeatherForcastCache {
    @Value("${forecast.cache.timeout.limit}")
    private long cacheTimeoutLimit;
    private final Map<String, WeatherForecastDto> forecastCache = new ConcurrentHashMap<>();

    public boolean isPresentInCache(String zipcode) {
        return forecastCache.containsKey(zipcode) && ChronoUnit.SECONDS.between(forecastCache.get(zipcode).getTimestamp(), LocalDateTime.now()) <= cacheTimeoutLimit;
    }

    public WeatherForecastDto getForecastCache(String zipcode) {
        return forecastCache.get(zipcode);
    }

    public void setForecastCache(String zipcode, WeatherForecastDto weatherForecastDto) {
        forecastCache.put(zipcode, weatherForecastDto);
    }

}
