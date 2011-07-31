package siia.business;

import java.util.Date;
import java.util.Map;

/**
 * @author Marius Bogoevici
 */
public class Flight {
    private String number;
    private Date scheduledDeparture;
    private String origin;
    private String destination;
    private Equipment aircraft;
    private Crew crew;
    private Map<Seat, Passenger> passengers;

    public Flight(String flightNumber) {
        this.number = flightNumber;
    }

    public Date getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(Date scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Equipment getAircraft() {
        return aircraft;
    }

    public void setAircraft(Equipment aircraft) {
        this.aircraft = aircraft;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public Map<Seat, Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Map<Seat, Passenger> passengers) {
        this.passengers = passengers;
    }
}


