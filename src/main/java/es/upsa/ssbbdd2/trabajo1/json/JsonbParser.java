package es.upsa.ssbbdd2.trabajo1.json;

import es.upsa.ssbbdd2.trabajo1.io.JsonParser;
import es.upsa.ssbbdd2.trabajo1.entities.City;
import es.upsa.ssbbdd2.trabajo1.entities.CityWeather;
import es.upsa.ssbbdd2.trabajo1.entities.Weather;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class JsonbParser implements JsonParser {
    private final Jsonb jsonb;

    public JsonbParser() {
        this.jsonb = JsonbBuilder.create();
    }
    @Override
    public City readCity(String jsonCity){
        return jsonb.fromJson(jsonCity, City.class);
    }

    @Override
    public Weather readWeather(String jsonWeather){
        return jsonb.fromJson(jsonWeather, Weather.class);
    }

    @Override
    public String write(CityWeather cityWeather){
        return jsonb.toJson(cityWeather);
    }


}
