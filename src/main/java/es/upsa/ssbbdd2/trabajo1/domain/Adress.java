package es.upsa.ssbbdd2.trabajo1.domain;

import java.util.Objects;

public class Adress {


    private String city;
    private String province;
    private String state;
    private String country;
    private String countryCode;
    private String archipelago;

    public Adress(){}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getArchipelago() {
        return archipelago;
    }

    public void setArchipelago(String archipelago) {
        this.archipelago = archipelago;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress = (Adress) o;
        return Objects.equals(city, adress.city) && Objects.equals(province, adress.province) && Objects.equals(state, adress.state) && Objects.equals(country, adress.country) && Objects.equals(countryCode, adress.countryCode) && Objects.equals(archipelago, adress.archipelago);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, province, state, country, countryCode, archipelago);
    }
}
