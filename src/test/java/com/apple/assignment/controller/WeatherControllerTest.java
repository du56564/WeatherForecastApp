package com.apple.assignment.controller;

import com.apple.assignment.constants.CommonConstants;
import com.apple.assignment.exception.CustomizedResponseEntityExceptionHandler;
import com.apple.assignment.model.TemperatureDto;
import com.apple.assignment.model.WeatherForecastDto;
import com.apple.assignment.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = WeatherController.class)
class WeatherControllerTest {
    @MockBean
    WeatherService weatherServiceMock;
    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetWeatherForecastLatest() throws Exception {
        String zipcode = "560076";
        WeatherForecastDto mockWeatherForecastDto = new WeatherForecastDto();
        mockWeatherForecastDto.setTemperatureDto(new TemperatureDto(25.0, 30.0, 24.0));
        mockWeatherForecastDto.setTimestamp(LocalDateTime.now());
        mockWeatherForecastDto.setCached(false);
        when(weatherServiceMock.getWeatherForcast(zipcode)).thenReturn(mockWeatherForecastDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/weather/v1/forecast")
                        .param("zipcode", zipcode)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currentTemperature").value(25.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.highTemperature").value(24.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lowTemperature").value(30.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperatureUnit").value(CommonConstants.DEGREE_CELSIUS))
                .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.indicator").value(CommonConstants.LATEST_TEMPERATURE));
    }
}
