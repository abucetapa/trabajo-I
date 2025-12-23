package es.upsa.ssbbdd2.trabajo1.json;

import es.upsa.ssbbdd2.trabajo1.domain.City;
import es.upsa.ssbbdd2.trabajo1.domain.CityWeather;
import es.upsa.ssbbdd2.trabajo1.domain.Weather;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;


public class JasonpParser implements JsonParser{

    @Override
    public City readCity(String jsonCity) {
        try(JsonReader reader = Json.)
    }

    @Override
    public Weather readWeather(String jsonWeather) {
        return null;
    }

    @Override
    public String write(CityWeather cityWeather) {
        return "";
    }
}
