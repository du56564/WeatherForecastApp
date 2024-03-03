package com.apple.assignment.model;

import java.time.LocalDateTime;

public class WeatherForecastDto {
    private LocalDateTime timestamp;
    private TemperatureDto temperatureDto;
    private boolean isCached;

    public WeatherForecastDto() {
    }

    public WeatherForecastDto(LocalDateTime timestamp, TemperatureDto temperatureDto, boolean isCached) {
        this.timestamp = timestamp;
        this.temperatureDto = temperatureDto;
        this.isCached = isCached;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TemperatureDto getTemperatureDto() {
        return temperatureDto;
    }

    public void setTemperatureDto(TemperatureDto temperatureDto) {
        this.temperatureDto = temperatureDto;
    }

    public boolean isCached() {
        return isCached;
    }

    public void setCached(boolean cached) {
        isCached = cached;
    }
}
