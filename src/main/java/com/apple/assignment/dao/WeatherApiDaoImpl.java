package com.apple.assignment.dao;

import com.apple.assignment.config.WeatherApiConfig;
import com.apple.assignment.exception.WeatherForcastBusinessException;
import com.apple.assignment.helper.WebClientHelper;
import com.apple.assignment.model.GeoCodeDto;
import com.apple.assignment.model.TemperatureDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class WeatherApiDaoImpl implements WeatherApiDao {
    private WeatherApiConfig apiConfig;
    private WebClientHelper webClientHelper;

    public WeatherApiDaoImpl() {
    }

    @Autowired
    public WeatherApiDaoImpl(WeatherApiConfig apiConfig, WebClientHelper webClientHelper) {
        this.apiConfig = apiConfig;
        this.webClientHelper = webClientHelper;
    }

    @Override
    public GeoCodeDto getGeoCode(String zipcode) throws WeatherForcastBusinessException {
        String geoCodeUrl = apiConfig.getGeoCodeApi(zipcode);
        Object geoCodeResponse = webClientHelper.getRestData(geoCodeUrl);
        if (geoCodeResponse != null) {
            List<Object> geoCodeObjectList = (List<Object>) geoCodeResponse;
            Map<String, Object> geoCodeObjectMap = (Map<String, Object>) geoCodeObjectList.get(0);
            String latitude = String.valueOf(geoCodeObjectMap.get("lat"));
            String longitude = String.valueOf(geoCodeObjectMap.get("lon"));
            GeoCodeDto geoCodeDto = new GeoCodeDto();
            geoCodeDto.setLatitude(latitude);
            geoCodeDto.setLongitude(longitude);
            return geoCodeDto;
        }
        return null;
    }

    @Override
    public TemperatureDto getTemperature(String longitude, String latitude) throws WeatherForcastBusinessException {
        String weatherForcastApi = apiConfig.getWeatherApi(latitude, longitude);
        Object weatherForcastResponse = webClientHelper.getRestData(weatherForcastApi);
        if (weatherForcastResponse != null) {
            Map<String, Object> weatherForcastMap = (Map<String, Object>) weatherForcastResponse;
            Map<String, Object> currentTempMap = (Map<String, Object>) weatherForcastMap.get("current");
            Map<String, Object> minMaxTempMap = (Map<String, Object>) weatherForcastMap.get("daily");

            Double currentTemperature = (Double) currentTempMap.get("temperature_2m");
            Double minimumTemperature = ((List<Double>) minMaxTempMap.get("temperature_2m_min")).get(0);
            Double maximumTemperature = ((List<Double>) minMaxTempMap.get("temperature_2m_max")).get(0);

            TemperatureDto temperatureDto = new TemperatureDto();
            temperatureDto.setCurrentTemperature(currentTemperature);
            temperatureDto.setMinTemperature(minimumTemperature);
            temperatureDto.setMaxTemperature(maximumTemperature);
            return temperatureDto;
        }
        return null;
    }

}
