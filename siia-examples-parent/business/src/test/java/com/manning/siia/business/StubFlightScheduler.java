package com.manning.siia.business;

import java.util.Date;

/**
 * @author Marius Bogoevici
 */
public class StubFlightScheduler implements FlightScheduler {

    public Flight nextFlightForNumber(String flightNumber) {
        Flight flight = new Flight(flightNumber);
        flight.setScheduledDeparture(new Date());
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        return flight;

    }
}
