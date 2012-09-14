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

package siia.booking.domain.flight;

import siia.booking.domain.Location;

import java.util.List;

/**
 * @author Jonas Partner
 */
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
