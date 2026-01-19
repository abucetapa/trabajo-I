package es.upsa.ssbbdd2.trabajo1.json;

import es.upsa.ssbbdd2.trabajo1.io.JsonParser;
import es.upsa.ssbbdd2.trabajo1.entities.*;
import jakarta.json.*;

import java.io.StringReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class JsonpParser implements JsonParser {

    @Override
    public City readCity(String jsonCity) {
        try (JsonReader reader = Json.createReader(new StringReader(jsonCity))) {
            JsonObject jsonObject = reader.readObject();

            var cityBuilder = City.builder();

            cityBuilder.withName(jsonObject.getString("name", null));
            cityBuilder.withDisplayName(jsonObject.getString("display_name", null));

            if (jsonObject.containsKey("population") && !jsonObject.isNull("population")) {
                cityBuilder.withPopulation(jsonObject.getJsonNumber("population").longValue());
            } else {
                cityBuilder.withPopulation(0);
            }

            JsonObject jsonAddress = jsonObject.getJsonObject("address");
            if (jsonAddress != null) {
                var address = Address.builder()
                        .withCity(jsonAddress.getString("city", null))
                        .withProvince(jsonAddress.getString("province", null))
                        .withState(jsonAddress.getString("state", null))
                        .withCountry(jsonAddress.getString("country", null))
                        .withCountryCode(jsonAddress.getString("country_code", null))
                        .withArchipelago(jsonAddress.getString("archipelago", null))
                        .build();
                cityBuilder.withAddress(address);
            }

            JsonValue locValue = jsonObject.get("location");
            if (locValue != null && locValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject locObj = (JsonObject) locValue;
                double lat = locObj.containsKey("latitude") ? locObj.getJsonNumber("latitude").doubleValue() : locObj.getJsonNumber("lat").doubleValue();
                double lon = locObj.containsKey("longitude") ? locObj.getJsonNumber("longitude").doubleValue() : locObj.getJsonNumber("lon").doubleValue();
                cityBuilder.withLocation(Location.builder().withLatitude(lat).withLongitude(lon).build());
            } else if (locValue != null && locValue.getValueType() == JsonValue.ValueType.ARRAY) {
                JsonArray locArray = (JsonArray) locValue;
                cityBuilder.withLocation(Location.builder()
                        .withLongitude(locArray.getJsonNumber(0).doubleValue())
                        .withLatitude(locArray.getJsonNumber(1).doubleValue())
                        .build());
            }

            JsonObject otherNamesJson = jsonObject.getJsonObject("other names");
            if (otherNamesJson != null) {
                Map<String, String> map = new HashMap<>();
                for (String key : otherNamesJson.keySet()) {
                    if (key.startsWith("name:")) {
                        map.put(key.substring(5), otherNamesJson.getString(key));
                    }
                }
                cityBuilder.withOtherNames(map);
            }

            return cityBuilder.build();
        }
    }

    @Override
    public Weather readWeather(String jsonWeather) {
        try (JsonReader reader = Json.createReader(new StringReader(jsonWeather))) {
            JsonObject root = reader.readObject();
            var weatherBuilder = Weather.builder();

            JsonArray weatherArray = root.getJsonArray("weather");
            if (weatherArray != null && !weatherArray.isEmpty()) {
                weatherBuilder.withDescription(weatherArray.getJsonObject(0).getString("description", ""));
            }

            JsonObject main = root.getJsonObject("main");
            if (main != null) {
                if (main.containsKey("temp")) weatherBuilder.withTemp(main.getJsonNumber("temp").doubleValue());
                if (main.containsKey("feels_like")) weatherBuilder.withFeelslike(main.getJsonNumber("feels_like").doubleValue());
                if (main.containsKey("humidity")) weatherBuilder.withHumidity(main.getJsonNumber("humidity").doubleValue());
            }

            JsonObject wind = root.getJsonObject("wind");
            if (wind != null && wind.containsKey("speed")) {
                weatherBuilder.withWindSpeed(wind.getJsonNumber("speed").doubleValue());
            }

            JsonObject clouds = root.getJsonObject("clouds");
            if (clouds != null && clouds.containsKey("all")) {
                weatherBuilder.withClouds(clouds.getJsonNumber("all").doubleValue());
            }

            if (root.containsKey("dt")) {
                weatherBuilder.withDateTime(unixToLocalDateTime(root.getJsonNumber("dt").longValue()));
            }

            JsonObject sys = root.getJsonObject("sys");
            if (sys != null) {
                if(sys.containsKey("sunrise")) weatherBuilder.withSunrise(unixToLocalDateTime(sys.getJsonNumber("sunrise").longValue()));
                if(sys.containsKey("sunset")) weatherBuilder.withSunset(unixToLocalDateTime(sys.getJsonNumber("sunset").longValue()));
            }

            return weatherBuilder.build();
        }
    }

    @Override
    public String write(CityWeather cityWeather) {
        City city = cityWeather.getCity();
        Weather weather = cityWeather.getWeather();

        JsonObjectBuilder addressBuilder = Json.createObjectBuilder();
        if (city.getAddress() != null) {
            addIfNotNull(addressBuilder, "city", city.getAddress().getCity());
            addIfNotNull(addressBuilder, "province", city.getAddress().getProvince());
            addIfNotNull(addressBuilder, "state", city.getAddress().getState());
            addIfNotNull(addressBuilder, "country", city.getAddress().getCountry());
            addIfNotNull(addressBuilder, "country_code", city.getAddress().getCountryCode());
        }

        JsonObjectBuilder otherNamesBuilder = Json.createObjectBuilder();
        if (city.getOtherNames() != null) {
            city.getOtherNames().forEach(otherNamesBuilder::add);
        }

        JsonObjectBuilder cityBuilder = Json.createObjectBuilder();
        addIfNotNull(cityBuilder, "name", city.getName());
        cityBuilder.add("population", city.getPopulation());
        cityBuilder.add("address", addressBuilder);
        cityBuilder.add("other names", otherNamesBuilder);

        if (city.getLocation() != null) {
            cityBuilder.add("location", Json.createArrayBuilder()
                    .add(city.getLocation().getLongitude())
                    .add(city.getLocation().getLatitude()));
        }

        JsonObjectBuilder weatherBuilder = Json.createObjectBuilder();
        if (weather != null) {
            if (weather.getDateTime() != null) weatherBuilder.add("dt", weather.getDateTime().toString());

            addIfNotNull(weatherBuilder, "description", weather.getDescription());

            weatherBuilder.add("temp", weather.getTemp());
            weatherBuilder.add("feels like", weather.getFeelslike());
            weatherBuilder.add("humidity", weather.getHumidity());
            weatherBuilder.add("wind speed", weather.getWindSpeed());
            weatherBuilder.add("clouds", weather.getClouds());

            if (weather.getSunrise() != null) weatherBuilder.add("sunrise", weather.getSunrise().toString());
            if (weather.getSunset() != null) weatherBuilder.add("sunset", weather.getSunset().toString());
        }

        return Json.createObjectBuilder()
                .add("city", cityBuilder)
                .add("weather", weatherBuilder)
                .build()
                .toString();
    }

    private LocalDateTime unixToLocalDateTime(long seconds) {
        return Instant.ofEpochSecond(seconds).atZone(ZoneId.of("Europe/Madrid")).toLocalDateTime();
    }

    private void addIfNotNull(JsonObjectBuilder builder, String key, String value) {
        if (value != null) {
            builder.add(key, value);
        }
    }
}