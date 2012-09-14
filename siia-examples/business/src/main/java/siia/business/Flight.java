/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package siia.business;

import java.util.Date;
import java.util.Map;

/**
 * @author Mark Fisher
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


