package es.upsa.ssbbdd2.trabajo1;

import es.upsa.ssbbdd2.trabajo1.io.JsonParser;
import es.upsa.ssbbdd2.trabajo1.io.NdjsonIO;
import es.upsa.ssbbdd2.trabajo1.entities.City;        // <--- Import correcto
import es.upsa.ssbbdd2.trabajo1.entities.CityWeather; // <--- Import correcto
import es.upsa.ssbbdd2.trabajo1.entities.Weather;     // <--- Import correcto
import es.upsa.ssbbdd2.trabajo1.openweather.OpenWeatherApi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UseCase {
    private final OpenWeatherApi openWeatherApi;

    public UseCase(String apiKey) {
        // Inicializamos la API con la clave proporcionada
        this.openWeatherApi = OpenWeatherApi.of(apiKey);
    }

    public void execute(File inputFile,
                        Predicate<City> predicate,
                        File newFile,
                        NdjsonIO ndjsonIO,
                        JsonParser jsonParser) throws Exception {

        System.out.println("--------------------------------------------------");
        System.out.println("Iniciando proceso para: " + newFile.getName());

        // 1. LEER: Obtenemos las ciudades del fichero aplicando el filtro
        List<City> cities = ndjsonIO.read(inputFile, predicate);
        System.out.println(" - Ciudades encontradas tras filtrar: " + cities.size());

        List<CityWeather> resultList = new ArrayList<>();

        // 2. PROCESAR: Para cada ciudad, consultamos el clima
        for (City city : cities) {
            try {
                // a) Llamada a OpenWeatherMap (latitud, longitud)
                String jsonWeather = openWeatherApi.requestCurrentWeather(
                        city.getLocation().getLatitude(),
                        city.getLocation().getLongitude()
                );

                // b) Parseamos el JSON recibido a objeto Weather
                Weather weather = jsonParser.readWeather(jsonWeather);

                // c) Creamos el objeto combinado usando el Builder (Lombok)
                CityWeather item = CityWeather.builder()
                        .withCity(city)
                        .withWeather(weather)
                        .build();

                resultList.add(item);

                // Pequeña pausa para respetar límites de la API
                Thread.sleep(100);

            } catch (Exception e) {
                System.err.println("Error obteniendo clima para " + city.getName() + ": " + e.getMessage());
            }
        }

        // 3. ESCRIBIR: Guardamos la lista final en el nuevo fichero
        ndjsonIO.write(resultList, newFile);

        System.out.println(" - Fichero generado: " + newFile.getAbsolutePath());
        System.out.println("Proceso finalizado con éxito.");
        System.out.println("--------------------------------------------------");
    }
}