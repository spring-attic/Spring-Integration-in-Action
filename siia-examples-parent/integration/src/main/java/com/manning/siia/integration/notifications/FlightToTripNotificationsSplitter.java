package com.manning.siia.integration.notifications;

import com.manning.siia.domain.trip.Trip;
import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.Splitter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Iwein Fuld
 */
public class FlightToTripNotificationsSplitter {

    @Splitter
    public List<TripNotification> generateTripNotificationsFrom(FlightNotification flightNotification, 
                                                                @Header("affectedTrips") List<Trip> affectedTrips){
        final List<TripNotification> notifications = new ArrayList<TripNotification>(affectedTrips.size());
        for (Trip trip : affectedTrips) {
            notifications.add(new TripNotification (trip, flightNotification.getMessage()));
        }
        return notifications;
    }
}
