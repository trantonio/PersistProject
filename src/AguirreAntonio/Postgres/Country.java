package AguirreAntonio.Postgres;

import java.io.Serializable;

public class Country implements Serializable {

    private static final long serialVersionUID = 93L;

    private Integer countryId;
    private String country;

    public Country() {
    }

    public Integer getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        return countryId != null ? countryId.equals(country.countryId) : country.countryId == null;
    }

    @Override
    public int hashCode() {
        return countryId != null ? countryId.hashCode() : 0;
    }
}
