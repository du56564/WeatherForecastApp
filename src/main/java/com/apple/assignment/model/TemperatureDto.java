package com.apple.assignment.model;

public class TemperatureDto {
    private double currentTemperature;
    private double minTemperature;
    private double maxTemperature;

    public TemperatureDto() {
    }

    public TemperatureDto(double currentTemperature, double minTemperature, double maxTemperature) {
        this.currentTemperature = currentTemperature;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
}
