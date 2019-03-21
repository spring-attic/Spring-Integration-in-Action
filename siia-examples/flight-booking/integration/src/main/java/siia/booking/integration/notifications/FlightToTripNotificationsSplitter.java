/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package siia.booking.integration.notifications;

import siia.booking.domain.notifications.FlightNotification;
import siia.booking.domain.notifications.TripNotification;
import siia.booking.domain.trip.Trip;
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
