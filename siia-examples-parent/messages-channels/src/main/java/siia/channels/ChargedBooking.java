package siia.channels;

import org.springframework.integration.annotation.MessageEndpoint;

/**
 * @author Marius Bogoevici
 */
@MessageEndpoint
public class ChargedBooking {

    private Booking booking;

    private Long confirmationNumber;

    public ChargedBooking(Booking booking, Long confirmationNumber) {
        this.booking = booking;
        this.confirmationNumber = confirmationNumber;
    }

    public Booking getBooking() {
        return booking;
    }

    public Long getConfirmationNumber() {
        return confirmationNumber;
    }
}
