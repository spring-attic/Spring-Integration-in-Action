/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package siia.booking.domain;

import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author Jonas Partner
 */
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
