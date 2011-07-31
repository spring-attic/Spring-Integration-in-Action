package siia.booking.domain;

import org.apache.commons.lang.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(Object obj) {
        if(! (obj instanceof Location)){
            return false;
        }
        Location otherLocation = (Location)obj;
        return otherLocation.getCity().equals(getCity())  && otherLocation.getCountryCode().equals(getCountryCode());
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getCity()).append(getCountryCode());
        return builder.toHashCode();
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getCountryCode()).append(" ").append(getCity());
        return buffer.toString();
    }
}
