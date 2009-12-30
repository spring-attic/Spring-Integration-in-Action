package com.manning.siia.domain.flight;

import com.manning.siia.domain.Location;

import java.util.Collections;
import java.util.List;


public class Flight {

    private final List<FlightSchedule> flightSchedules;

    private final String carrierId;

    private final int flightNumber;

    private final Location departureAirport;

    private final Location destinationAirport;

    private final List<Location> stops;

    public Flight(List<FlightSchedule> flightSchedules, String carrierId, int flightNumber,
                  Location departureAirport, Location destinationAirport, List<Location> stops) {
        this.flightSchedules = flightSchedules;
        this.carrierId = carrierId;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.stops = stops;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public Location getDepartureAirport() {
        return departureAirport;
    }

    public Location getDestinationAirport() {
        return destinationAirport;
    }

    public List<Location> getStops() {
        return stops;
    }
}
