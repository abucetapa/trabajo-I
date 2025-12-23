package es.upsa.ssbbdd2.trabajo1.openweather;


import es.upsa.ssbbdd2.trabajo1.openweather.impl.OpenWeatherApiImpl;

public interface OpenWeatherApi
{
    public static OpenWeatherApi of(String apiKey)
    {
        return new OpenWeatherApiImpl(apiKey);
    }

    public String requestCurrentWeather(double latitude, double longitude) throws OpenWeatherException;
}
