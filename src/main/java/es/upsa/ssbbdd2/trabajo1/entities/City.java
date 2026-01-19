package es.upsa.ssbbdd2.trabajo1.entities;

import es.upsa.ssbbdd2.trabajo1.json.adapters.LocationAdapter;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import java.util.Map;

@Data
@Builder(setterPrefix = "with")
@With
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private String name;

    @JsonbProperty("other names")
    private Map<String, String> otherNames;

    @JsonbProperty("display_name")
    private String displayName;

    private long population;
    private Address address;

    @JsonbTypeAdapter(LocationAdapter.class)
    private Location location;
}