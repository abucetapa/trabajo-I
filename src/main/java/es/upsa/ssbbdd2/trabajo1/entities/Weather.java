package es.upsa.ssbbdd2.trabajo1.entities;

import es.upsa.ssbbdd2.trabajo1.json.adapters.LocalDateTimeAdapter;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.LocalDateTime;

@Data
@Builder(setterPrefix = "with")
@With
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    private String description;
    private double temp;

    @JsonbProperty("feels like")
    private double feelslike;

    private double humidity;

    @JsonbProperty("wind speed")
    private double windSpeed;

    private double clouds;

    @JsonbProperty("dt")
    @JsonbTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime dateTime;

    @JsonbTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime sunrise;

    @JsonbTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime sunset;
}