package com.manning.siia.integration.purchases;

import com.manning.siia.domain.trip.Leg;
import com.manning.siia.domain.trip.Trip;

import java.util.List;

/**
 * @author Iwein Fuld
 */
public class LegQuoteAggregator {

    public Trip suggestTripFromQuotes(List<Leg> legs){
        return new Trip(legs);
    }

}
