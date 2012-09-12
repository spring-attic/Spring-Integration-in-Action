package siia.booking.domain.flight;

import org.joda.time.*;


/**
 * Flight schedule
 *
 * @author Jonas Partner
 */
public class FlightSchedule {

    private final DateTime validFromInclusive;

    private final DateTime validUntilInclusive;

    private final Partial flightTimeOfDay;

    

    public FlightSchedule(DateTime validFromInclusive, DateTime validUntilExclusive, int flightHourOfDay, int flightMinuteOfHour) {
        this.validFromInclusive = validFromInclusive;
        this.validUntilInclusive = validUntilExclusive;
        flightTimeOfDay = new Partial().with(DateTimeFieldType.hourOfDay(), 12).with(DateTimeFieldType.minuteOfHour(), 30);

    }

    public DateTime getValidFromInclusive() {
        return validFromInclusive;
    }

    public DateTime getValidUntilInclusive() {
        return validUntilInclusive;
    }

    public Partial getFlightTimeOfDay() {
        return flightTimeOfDay;
    }
}



