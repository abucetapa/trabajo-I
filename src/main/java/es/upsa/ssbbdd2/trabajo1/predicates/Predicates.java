package es.upsa.ssbbdd2.trabajo1.predicates;

import es.upsa.ssbbdd2.trabajo1.entities.City;
import java.util.function.Predicate;

public class Predicates {
    private Predicates() {}

    public static Predicate<City> cityBelongsTo(String state) {
        return city -> {
            if (city.getAddress() == null) return false;
            String cityState = city.getAddress().getState();
            if (cityState != null) return cityState.equals(state);
            return state.equals(city.getAddress().getArchipelago());
        };
    }

    public static Predicate<City> cityHasPopulationGreaterThan(int population) {
        return city -> city.getPopulation() > population;
    }
}