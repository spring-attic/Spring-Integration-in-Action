package com.manning.siia.integration.notifications;

import com.manning.siia.domain.flight.Flight;
import com.manning.siia.domain.trip.Trip;
import com.manning.siia.domain.trip.TripRepository;

import java.util.List;

/**
 *
 * @author Iwein Fuld
 */
public class FlightNotification {
    private final String message;
    private final Flight flight;

    private Priority priority;

    public FlightNotification(String message, Flight flight) {
        this.message = message;
        this.flight = flight;
    }

    public String getMessage() {
        return message;
    }

    public Flight getFlight() {
        return flight;
    }

    public List<Trip> findRelatedTrips(TripRepository tripRepository) {
        return tripRepository.findTripsRelatedTo(this.flight);
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
