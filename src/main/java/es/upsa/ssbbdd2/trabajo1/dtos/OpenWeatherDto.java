package es.upsa.ssbbdd2.trabajo1.dtos;

import es.upsa.ssbbdd2.trabajo1.entities.Weather;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Data
public class OpenWeatherDto {

    private MainDto main;
    private WindDto wind;
    private CloudsDto clouds;
    private List<WeatherItemDto> weather;
    private SysDto sys;
    private Long dt;


    @Data
    public static class MainDto {
        private Double temp;
        @JsonbProperty("feels_like")
        private Double feelsLike;
        private Double humidity;
    }

    @Data
    public static class WindDto {
        private Double speed;
    }

    @Data
    public static class CloudsDto {
        private Double all;
    }

    @Data
    public static class WeatherItemDto {
        private String description;
    }

    @Data
    public static class SysDto {
        private Long sunrise;
        private Long sunset;
    }

    public Weather toWeather() {
        var builder = Weather.builder();

        // 1. Fechas
        if (dt != null) builder.withDateTime(unixToDate(dt));
        if (sys != null) {
            if (sys.sunrise != null) builder.withSunrise(unixToDate(sys.sunrise));
            if (sys.sunset != null) builder.withSunset(unixToDate(sys.sunset));
        }

        // 2. Main Data (Temp, Humidity)
        if (main != null) {
            if (main.temp != null) builder.withTemp(main.temp);
            if (main.feelsLike != null) builder.withFeelslike(main.feelsLike);
            if (main.humidity != null) builder.withHumidity(main.humidity);
        }

        // 3. Wind
        if (wind != null && wind.speed != null) {
            builder.withWindSpeed(wind.speed);
        }

        // 4. Clouds (Aquí arreglamos el error original)
        if (clouds != null && clouds.all != null) {
            builder.withClouds(clouds.all);
        }

        // 5. Description
        if (weather != null && !weather.isEmpty()) {
            builder.withDescription(weather.get(0).getDescription());
        }

        return builder.build();
    }

    private LocalDateTime unixToDate(long seconds) {
        return Instant.ofEpochSecond(seconds)
                .atZone(ZoneId.of("Europe/Madrid"))
                .toLocalDateTime();
    }
}