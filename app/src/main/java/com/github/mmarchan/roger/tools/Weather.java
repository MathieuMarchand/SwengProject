package com.github.mmarchan.roger.tools;

public class Weather {

    private double temperature;
    private String weather;

    public Weather(double temperature, String weather){
        this.temperature = temperature;
        this.weather = weather;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getWeather(){
        return weather;
    }
}
