package siia.channels;

/**
 * @author Marius Bogoevici
 */
public class SeatConfirmation {

    private ChargedBooking chargedBooking;

    private Seat seat;

    public SeatConfirmation(ChargedBooking chargedBooking, Seat seat) {

        this.chargedBooking = chargedBooking;
        this.seat = seat;
    }

    public ChargedBooking getChargedBooking() {
        return chargedBooking;
    }

    public Seat getSeat() {
        return seat;
    }
}
