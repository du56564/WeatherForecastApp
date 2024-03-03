package com.apple.assignment.model;

import java.time.LocalDateTime;

public class WeatherForecastResponse {

    private double currentTemperature;
    private double highTemperature;
    private double lowTemperature;
    private String temperatureUnit;
    private String indicator;
    private LocalDateTime timestamp;

    public WeatherForecastResponse() {
    }

    public WeatherForecastResponse(double currentTemperature, double highTemperature, double lowTemperature, String temperatureUnit, String indicator, LocalDateTime timestamp) {
        this.currentTemperature = currentTemperature;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.temperatureUnit = temperatureUnit;
        this.indicator = indicator;
        this.timestamp = timestamp;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public double getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(double highTemperature) {
        this.highTemperature = highTemperature;
    }

    public double getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(double lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
