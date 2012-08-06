package siia.channels;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * @author Marius Bogoevici
 */
@MessageEndpoint
public class BillForBookingService {

    @ServiceActivator
    public ChargedBooking pay(Booking booking) {
        // pay for the booking
        return new ChargedBooking(booking, 1l);
    }
}
