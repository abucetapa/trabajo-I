package es.upsa.ssbbdd2.trabajo1.openweather.impl;

import es.upsa.ssbbdd2.trabajo1.openweather.OpenWeatherApi;
import es.upsa.ssbbdd2.trabajo1.openweather.OpenWeatherException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class OpenWeatherApiImpl implements OpenWeatherApi
{
    final String OPEN_WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    final String OPENWEATHER_CURRENT_WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s&units=metric&lang=es";
    final String OPENWEATHER_FORECAST_URL = "https://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&appid=%s&units=metric&lang=es&cnt=5";

    private final String apiKey;
    private final HttpClient httpClient;

    public OpenWeatherApiImpl(String apiKey)
    {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public String requestCurrentWeather(double latitude, double longitude) throws OpenWeatherException
    {
        return request( OPENWEATHER_CURRENT_WEATHER_URL.formatted(convert(latitude), convert(longitude), apiKey)  );
    }


    private String request(String uri) throws OpenWeatherException
    {
        try
        {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                                                 .uri( URI.create( uri ) )
                                                 .header("Accept", "application/json")
                                                 .GET()
                                                 .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return httpResponse.body();

        } catch (IOException | InterruptedException  exception)
        {
            throw new OpenWeatherException(exception);

        }
    }

    public static String convert(double latlon)
    {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#0.#######", symbols);
        return df.format(latlon);
    }
}
