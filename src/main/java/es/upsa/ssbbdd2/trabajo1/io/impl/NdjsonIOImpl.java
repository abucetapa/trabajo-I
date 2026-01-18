package es.upsa.ssbbdd2.trabajo1.io.impl;

import es.upsa.ssbbdd2.trabajo1.io.JsonParser;
import es.upsa.ssbbdd2.trabajo1.io.NdjsonIO;
import es.upsa.ssbbdd2.trabajo1.entities.City;
import es.upsa.ssbbdd2.trabajo1.entities.CityWeather;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class NdjsonIOImpl implements NdjsonIO {

    private final JsonParser jsonParser;

    public NdjsonIOImpl(JsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    @Override
    public List<City> read(File file, Predicate<City> predicate) throws IOException {
        List<City> cities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                City city = jsonParser.readCity(line);

                if (predicate == null || predicate.test(city)) {
                    cities.add(city);
                }
            }
        }
        return cities;
    }

    @Override
    public void write(List<CityWeather> citiesWithWeather, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (CityWeather item : citiesWithWeather) {
                String jsonLine = jsonParser.write(item);
                writer.write(jsonLine);
                writer.newLine();
            }
        }
    }
}