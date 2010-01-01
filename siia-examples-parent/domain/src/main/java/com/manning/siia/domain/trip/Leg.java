package com.manning.siia.domain.trip;

import com.manning.siia.domain.Location;
import com.manning.siia.domain.binding.JodaDateTimeBinder;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class Leg {

    @XmlElement @XmlJavaTypeAdapter(JodaDateTimeBinder.class)
    private DateTime startOfLegDate;

    @XmlElement @XmlJavaTypeAdapter(JodaDateTimeBinder.class)
    private DateTime endOfLegDate;

    @XmlElement
    private Location startLocation;

    @XmlElement
    private Location endLocation;

    public Leg(){}

    public Leg(DateTime startOfLeg, DateTime endOfLeg, Location startLocation, Location endLocation) {
        this.startOfLegDate = startOfLeg;
        this.endOfLegDate = endOfLeg;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public DateTime getStartOfLegDate() {
        return startOfLegDate;
    }

    public DateTime getEndOfLegDate() {
        return endOfLegDate;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }
}
