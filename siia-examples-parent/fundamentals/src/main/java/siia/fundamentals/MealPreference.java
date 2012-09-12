package siia.fundamentals;

/**
 * @author Marius Bogoevici
 */
public class MealPreference {

    private Long bookingId;

    private Long flightReference;

    public Long getBookingId() {
        return bookingId;
    }

    public Long getFlightReference() {
        return flightReference;
    }

    public void setFlightReference(Long flightReference) {
        this.flightReference = flightReference;
    }

    @Override
    public String toString() {
        return "MealPreference{" +
                "bookingId=" + bookingId +", " +
                "flightReference=" + flightReference + '}';
    }
}
