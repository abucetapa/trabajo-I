package es.upsa.ssbbdd2.trabajo1.json;

import es.upsa.ssbbdd2.trabajo1.io.JsonParser;
import es.upsa.ssbbdd2.trabajo1.entities.City;
import es.upsa.ssbbdd2.trabajo1.entities.CityWeather;
import es.upsa.ssbbdd2.trabajo1.entities.Weather;
import es.upsa.ssbbdd2.trabajo1.dtos.OpenWeatherDto;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class JsonbParser implements JsonParser {
    private final Jsonb jsonb;

    public JsonbParser() {
        this.jsonb = JsonbBuilder.create();
    }

    @Override
    public City readCity(String jsonCity) {
        return jsonb.fromJson(jsonCity, City.class);
    }

    @Override
    public Weather readWeather(String jsonWeather) {
        OpenWeatherDto dto = jsonb.fromJson(jsonWeather, OpenWeatherDto.class);

        return dto.toWeather();
    }

    @Override
    public String write(CityWeather cityWithWeather) {
        return jsonb.toJson(cityWithWeather);
    }
}