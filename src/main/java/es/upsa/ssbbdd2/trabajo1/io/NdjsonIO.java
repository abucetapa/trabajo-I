package es.upsa.ssbbdd2.trabajo1.io;

import es.upsa.ssbbdd2.trabajo1.entities.City;
import es.upsa.ssbbdd2.trabajo1.entities.CityWeather;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public interface NdjsonIO {

    public List<City> read(File file, Predicate<City> predicate) throws IOException;
    void write(List<CityWeather> citiesWeather, File file) throws IOException;
}
