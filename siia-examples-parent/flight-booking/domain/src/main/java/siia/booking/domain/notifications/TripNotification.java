package siia.booking.domain.notifications;

import siia.booking.domain.trip.Trip;

/**
 * @author Iwein Fuld
 */
public class TripNotification {
    private final Trip trip;
    private final String message;

    public TripNotification(Trip trip, String message) {
        this.trip = trip;
        this.message = message;
    }

    public Trip getTrip() {
        return trip;
    }

    public String getMessage() {
        return message;
    }
}
