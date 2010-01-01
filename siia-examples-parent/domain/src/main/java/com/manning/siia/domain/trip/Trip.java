package com.manning.siia.domain.trip;

import java.util.Collections;
import java.util.List;

/**
 * @author Jonas Partner
 */
public class Trip {

    private final List<Leg> legs;

    public Trip(List<Leg> legs) {
        this.legs = legs;
    }

    public List<Leg> getLegs() {
        return Collections.unmodifiableList(legs);
    }
}
