package AitorRodriguez.hibernate;

import java.io.Serializable;

public class City implements Serializable {

    private static final long serialVersionUID = 93L;

    private Integer cityId;
    private String cosa;

    public City() {
    }

    public City(Integer cityId, String cosa) {
        this.cityId = cityId;
        this.cosa = cosa;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCosa() {
        return cosa;
    }

    public void setCosa(String cosa) {
        this.cosa = cosa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (cityId != null ? !cityId.equals(city.cityId) : city.cityId != null) return false;
        return cosa != null ? cosa.equals(city.cosa) : city.cosa == null;
    }

    @Override
    public int hashCode() {
        int result = cityId != null ? cityId.hashCode() : 0;
        result = 31 * result + (cosa != null ? cosa.hashCode() : 0);
        return result;
    }
}