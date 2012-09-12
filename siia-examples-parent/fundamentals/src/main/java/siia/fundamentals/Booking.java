package siia.fundamentals;

/**
 * @author Marius Bogoevici
 */
public class Booking {

    private Long flightRef;

    public Booking(Long flightRef) {
        this.flightRef = flightRef;
    }

    public Long getFlightRef() {
        return flightRef;
    }
}
