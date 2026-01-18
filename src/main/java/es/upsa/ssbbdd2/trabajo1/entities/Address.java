package es.upsa.ssbbdd2.trabajo1.entities;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@Builder(setterPrefix = "with")
@With
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    // Atributos básicos
    private String city;
    private String province;
    private String state;
    private String country;

    // Mapeo especial para JSON-B (el JSON trae "country_code")
    @JsonbProperty("country_code")
    private String countryCode;

    private String archipelago;
}