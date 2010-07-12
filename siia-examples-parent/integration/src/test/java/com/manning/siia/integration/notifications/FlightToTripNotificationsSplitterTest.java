package com.manning.siia.integration.notifications;

import com.manning.siia.domain.trip.Leg;
import com.manning.siia.domain.trip.Trip;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.hamcrest.Matchers.*;

/**
 * @author Iwein Fuld
 */
public class FlightToTripNotificationsSplitterTest {

    private final FlightToTripNotificationsSplitter splitter = new FlightToTripNotificationsSplitter();

    @Test
    public void shouldReturnTripNotificationForEachAffectedTrip() {
        FlightNotification flightNotification = new FlightNotification("Flight is cancelled");
        List<Trip> affectedTrips = new ArrayList<Trip>(5);
        affectedTrips.add(new Trip(Collections.singletonList(mock(Leg.class))));
        List<TripNotification> notifications = splitter.generateTripNotificationsFrom(flightNotification, affectedTrips);
        for (TripNotification notification : notifications) {
            assertThat(affectedTrips, hasItem(notification.getTrip()));
        }
    }

}
