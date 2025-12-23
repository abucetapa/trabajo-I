package es.upsa.ssbbdd2.trabajo1.domain;

import java.util.Map;
import java.util.Objects;

public class City {

    private String name;
    private Map<String, String> otherNames;
    private String displayName;
    private long population;
    private Adress adress;
    private Location location;

    public City() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(Map<String, String> otherNames) {
        this.otherNames = otherNames;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return population == city.population && Objects.equals(name, city.name) && Objects.equals(otherNames, city.otherNames) && Objects.equals(displayName, city.displayName) && Objects.equals(adress, city.adress) && Objects.equals(location, city.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, otherNames, displayName, population, adress, location);
    }
}
