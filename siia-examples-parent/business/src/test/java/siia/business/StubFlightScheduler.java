package siia.business;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class StubFlightScheduler implements FlightScheduler {

  public Flight nextFlightForNumber(String flightNumber) {
    Flight flight = new Flight(flightNumber);
    flight.setScheduledDeparture(new Date());
    flight.setOrigin("JFK");
    flight.setDestination("LAX");
    return flight;
  }
}
