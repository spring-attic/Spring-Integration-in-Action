package com.manning.siia.integration.notifications;

import com.manning.siia.domain.trip.Trip;
import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.Splitter;

import java.util.ArrayList;
import java.util.List;

/**
 * This splitter takes in a FlightNotification and a list of affected trips and outputs a TripNotification for each
 * affected trip. The affected trips are determined prior to the splitting by a header enricher (see
 * flight-notifications.xml).
 *
 * @author Iwein Fuld
 */
public class FlightToTripNotificationsSplitter {

    @Splitter
    public List<TripNotification> generateTripNotificationsFrom(FlightNotification flightNotification,
                                                                @Header("affectedTrips") List<Trip> affectedTrips) {
        List<TripNotification> notifications = new ArrayList<TripNotification>(affectedTrips.size());
        for (Trip trip : affectedTrips) {
            notifications.add(new TripNotification(trip, flightNotification.getMessage()));
        }
        return notifications;
    }
}
