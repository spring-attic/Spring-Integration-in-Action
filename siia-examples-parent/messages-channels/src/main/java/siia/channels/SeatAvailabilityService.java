package siia.channels;

import org.springframework.integration.annotation.ServiceActivator;

/**
 * @author Marius Bogoevici
 */
public class SeatAvailabilityService {

    @ServiceActivator
    public SeatConfirmation confirmSeat(ChargedBooking chargedBooking) {
        Seat seat = new Seat("1A");
        return new SeatConfirmation(chargedBooking, seat);
    }
}
