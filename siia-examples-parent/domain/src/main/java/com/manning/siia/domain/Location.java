package com.manning.siia.domain;

import javax.xml.bind.annotation.XmlAttribute;


public class Location {

    @XmlAttribute
    private String countryCode;

    @XmlAttribute
    private String city;

    private Location(){}

    public Location(String countryCode, String city) {
        this.countryCode = countryCode;
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCity() {
        return city;
    }
}
