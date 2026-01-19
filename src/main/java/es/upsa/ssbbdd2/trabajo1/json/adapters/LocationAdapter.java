package es.upsa.ssbbdd2.trabajo1.json.adapters;

import es.upsa.ssbbdd2.trabajo1.entities.Location;
import jakarta.json.bind.adapter.JsonbAdapter;

public class LocationAdapter implements JsonbAdapter<Location, Double[]> {

    @Override
    public Double[] adaptToJson(Location location) throws Exception {
        if (location == null) return null;
        return new Double[] { location.getLongitude(), location.getLatitude() };
    }

    @Override
    public Location adaptFromJson(Double[] coords) throws Exception {
        if (coords == null || coords.length < 2) return null;

        return Location.builder()
                .withLongitude(coords[0])
                .withLatitude(coords[1])
                .build();
    }
}