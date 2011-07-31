package siia.booking.domain.flight;

import org.joda.time.DateTime;


public class FlightBooking {

    private final DateTime bookingDateTime;

    private final String bookingConfirmation;

    private final Flight flight;

    public FlightBooking(DateTime bookingDateTime, String bookingConfirmation, Flight flight) {
        this.bookingDateTime = bookingDateTime;
        this.bookingConfirmation = bookingConfirmation;
        this.flight = flight;
    }

    public DateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public String getBookingConfirmation() {
        return bookingConfirmation;
    }

    public Flight getFlight() {
        return flight;
    }
}
