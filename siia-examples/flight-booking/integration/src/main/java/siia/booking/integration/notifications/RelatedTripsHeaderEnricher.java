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

import java.util.List;

import org.springframework.util.Assert;

import siia.booking.domain.notifications.FlightNotification;
import siia.booking.domain.trip.Trip;
import siia.booking.domain.trip.TripRepository;

/**
 * @author Iwein Fuld
 */
public class RelatedTripsHeaderEnricher {

	private TripRepository tripRepository;

	public void setTripRepository(TripRepository tripRepository) {
		this.tripRepository = tripRepository;
	}

	public List<Trip> relatedTripsForFlight(FlightNotification notification) {
		Assert.notNull(this.tripRepository, "tripRepository is required");
		return this.tripRepository.findTripsRelatedTo(notification.getFlight());
	}
}
