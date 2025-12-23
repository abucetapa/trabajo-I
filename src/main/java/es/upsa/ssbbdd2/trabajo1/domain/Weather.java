package es.upsa.ssbbdd2.trabajo1.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Weather {

    private String description;
    private double temp;
    private double feelsLike;
    private double humidity;
    private double windSpeed;
    private double clouds;
    private LocalDateTime dateTime;
    private LocalDate sunrise;
    private LocalDate sunset;

    public Weather() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getClouds() {
        return clouds;
    }

    public void setClouds(double clouds) {
        this.clouds = clouds;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDate getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalDate sunrise) {
        this.sunrise = sunrise;
    }

    public LocalDate getSunset() {
        return sunset;
    }

    public void setSunset(LocalDate sunset) {
        this.sunset = sunset;
    }
}
