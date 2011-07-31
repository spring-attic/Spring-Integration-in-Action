package siia.booking.integration.notifications;

import siia.booking.domain.flight.Flight;
import siia.booking.domain.notifications.FlightNotification;
import siia.booking.domain.notifications.TripNotification;
import siia.booking.domain.trip.Leg;
import siia.booking.domain.trip.Trip;
import org.junit.Test;
import siia.booking.integration.notifications
    .FlightToTripNotificationsSplitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Iwein Fuld
 */
public class FlightToTripNotificationsSplitterTest {

    private final FlightToTripNotificationsSplitter splitter = new FlightToTripNotificationsSplitter();

    @Test
    public void shouldReturnTripNotificationForEachAffectedTrip() {
        FlightNotification flightNotification = new FlightNotification("Flight is cancelled", mock(Flight.class));
        List<Trip> affectedTrips = new ArrayList<Trip>(5);
        affectedTrips.add(new Trip(Collections.singletonList(mock(Leg.class))));
        List<TripNotification> notifications = splitter.generateTripNotificationsFrom(flightNotification, affectedTrips);
        for (TripNotification notification : notifications) {
            assertThat(affectedTrips, hasItem(notification.getTrip()));
        }
    }

}
