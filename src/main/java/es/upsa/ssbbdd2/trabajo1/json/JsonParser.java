package es.upsa.ssbbdd2.trabajo1.json;

import es.upsa.ssbbdd2.trabajo1.domain.*;


public interface JsonParser {

    public City readCity(String jsonCity);
    public Weather readWeather(String jsonWeather);
    public String write(CityWeather cityWeather);


}
