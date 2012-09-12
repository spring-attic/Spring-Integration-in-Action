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

package siia.booking.integration.notifications;

import siia.booking.domain.flight.Flight;
import siia.booking.domain.notifications.FlightNotification;
import siia.booking.domain.notifications.TripNotification;
import siia.booking.domain.trip.Leg;
import siia.booking.domain.trip.Trip;
import org.junit.Test;
import siia.booking.integration.notifications
    .FlightToTripNotificationsSplitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Iwein Fuld
 */
public class FlightToTripNotificationsSplitterTest {

    private final FlightToTripNotificationsSplitter splitter = new FlightToTripNotificationsSplitter();

    @Test
    public void shouldReturnTripNotificationForEachAffectedTrip() {
        FlightNotification flightNotification = new FlightNotification("Flight is cancelled", mock(Flight.class));
        List<Trip> affectedTrips = new ArrayList<Trip>(5);
        affectedTrips.add(new Trip(Collections.singletonList(mock(Leg.class))));
        List<TripNotification> notifications = splitter.generateTripNotificationsFrom(flightNotification, affectedTrips);
        for (TripNotification notification : notifications) {
            assertThat(affectedTrips, hasItem(notification.getTrip()));
        }
    }

}
