package es.upsa.ssbbdd2.trabajo1;

import es.upsa.ssbbdd2.trabajo1.openweather.OpenWeatherApi;
import java.io.File;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        File file = new File("cities.ndjson");
        File jsonbFile = new File("cities_jsonb.ndjson");
        File jsonpFile = new File("cities_jsonp.ndjson");

        OpenWeatherApi openWeatherApi = OpenWeatherApi.of("escribe aquí tu API KEY");
        String weatherJSON = openWeatherApi.requestCurrentWeather(40.9651572, -5.6640182);
    }
}
