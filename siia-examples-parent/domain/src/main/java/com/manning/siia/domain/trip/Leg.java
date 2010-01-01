package com.manning.siia.domain.trip;

import com.manning.siia.domain.Location;
import org.joda.time.DateTime;

public class Leg {

    private DateTime startOfLeg;

    private DateTime endOfLeg;

    private Location startLocation;

    private Location endLocation;

    public Leg(){}

    public Leg(DateTime startOfLeg, DateTime endOfLeg, Location startLocation, Location endLocation) {
        this.startOfLeg = startOfLeg;
        this.endOfLeg = endOfLeg;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public DateTime getStartOfLeg() {
        return startOfLeg;
    }

    public DateTime getEndOfLeg() {
        return endOfLeg;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }
}
