package es.upsa.ssbbdd2.trabajo1.domain;

public class CityWeather {

    private City city;
    private Weather weather;

    public CityWeather() {}

    public CityWeather(Weather weather, City city) {
        this.weather = weather;
        this.city = city;
    }

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }

    public Weather getWeather() {
        return weather;
    }
    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
