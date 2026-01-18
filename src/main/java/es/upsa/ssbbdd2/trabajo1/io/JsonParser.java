package es.upsa.ssbbdd2.trabajo1.io;

import es.upsa.ssbbdd2.trabajo1.entities.City;
import es.upsa.ssbbdd2.trabajo1.entities.CityWeather;
import es.upsa.ssbbdd2.trabajo1.entities.Weather;


public interface JsonParser {

    public City readCity(String jsonCity);
    public Weather readWeather(String jsonWeather);
    public String write(CityWeather cityWeather);


}
