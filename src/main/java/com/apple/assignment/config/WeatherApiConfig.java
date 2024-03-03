package com.apple.assignment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherApiConfig {

    private String weatherApiKey;
    private String geoCodeApi;

    @Autowired
    public WeatherApiConfig(@Value("${weather.api.key}") String weatherApiKey, @Value("${geocode.api.key}") String geoCodeApi) {
        this.weatherApiKey = weatherApiKey;
        this.geoCodeApi = geoCodeApi;

    }

    public String getWeatherApi(String latitude, String longitude) {
        return weatherApiKey.replace("{{latitude}}", latitude).replace("{{longitude}}", longitude);
    }

    public String getGeoCodeApi(String zipcode) {
        return geoCodeApi.replace("{{zipcode}}", zipcode);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }


}
