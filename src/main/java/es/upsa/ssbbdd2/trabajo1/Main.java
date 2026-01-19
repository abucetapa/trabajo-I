package es.upsa.ssbbdd2.trabajo1;

import es.upsa.ssbbdd2.trabajo1.io.impl.NdjsonIOImpl;
import es.upsa.ssbbdd2.trabajo1.json.JsonbParser;
import es.upsa.ssbbdd2.trabajo1.json.JsonpParser;
import es.upsa.ssbbdd2.trabajo1.predicates.Predicates;
import es.upsa.ssbbdd2.trabajo1.entities.City; // <--- Import correcto

import java.io.File;
import java.util.function.Predicate;

public class Main {

    // ¡IMPORTANTE! Sustituye esto por tu API Key real de OpenWeatherMap
    private static final String API_KEY = "26d8744760cc337585d4319b9b3aee0b";

    public static void main(String[] args) {
        try {
            File inputFile = new File("cities.ndjson");
            File jsonbFile = new File("cities_jsonb.ndjson");
            File jsonpFile = new File("cities_jsonp.ndjson");

            Predicate<City> filter = Predicates.cityBelongsTo("Castilla y León")
                    .and(Predicates.cityHasPopulationGreaterThan(75_000));

            UseCase useCase = new UseCase(API_KEY);

            System.out.println("=== EJECUCIÓN JSON-B ===");
            JsonbParser jsonbParser = new JsonbParser();
            NdjsonIOImpl ioForJsonb = new NdjsonIOImpl(jsonbParser);

            useCase.execute(inputFile, filter, jsonbFile, ioForJsonb, jsonbParser);

            System.out.println("\n=== EJECUCIÓN JSON-P ===");
            JsonpParser jsonpParser = new JsonpParser();
            NdjsonIOImpl ioForJsonp = new NdjsonIOImpl(jsonpParser);

            useCase.execute(inputFile, filter, jsonpFile, ioForJsonp, jsonpParser);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}