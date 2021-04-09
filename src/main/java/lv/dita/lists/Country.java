package lv.dita.lists;

import java.util.*;

public class Country {
    private String code;
    private String name;
    List<Country> countries = new ArrayList<>();
    String[] isoCountries = Locale.getISOCountries();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public String[] getIsoCountries() {
        return isoCountries;
    }

    public void setIsoCountries(String[] isoCountries) {
        this.isoCountries = isoCountries;
    }

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCountry() {
        String countryName = "";
        for (String country : isoCountries) {
            Locale locale = new Locale("en", country);
            String name = locale.getDisplayCountry();

            if (!"".equals(code) && !"".equals(name)) {
                countries.add(new Country(code, name));
            }
            for (Country countryInList : countries) {
                countryName = countryInList.getName();
            }
        }
        return countryName;
    }
}



