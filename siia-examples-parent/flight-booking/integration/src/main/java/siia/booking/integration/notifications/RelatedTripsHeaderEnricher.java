package siia.booking.integration.notifications;

import java.util.List;

import org.springframework.util.Assert;

import siia.booking.domain.notifications.FlightNotification;
import siia.booking.domain.trip.Trip;
import siia.booking.domain.trip.TripRepository;

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
