package com.example.saturday2.models;

/**
 * Created by junsuk on 2016. 12. 31..
 */

public class Weather {
    private String country;
    private String weather;
    private String temperature;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
