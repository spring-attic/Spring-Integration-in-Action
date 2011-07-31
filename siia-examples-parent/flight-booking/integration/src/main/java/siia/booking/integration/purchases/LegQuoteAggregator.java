package siia.booking.integration.purchases;

import siia.booking.domain.trip.Leg;
import siia.booking.domain.trip.Trip;

import java.util.List;

/**
 * @author Iwein Fuld
 */
public class LegQuoteAggregator {

    public Trip suggestTripFromQuotes(List<Leg> legs){
        return new Trip(legs);
    }

}
