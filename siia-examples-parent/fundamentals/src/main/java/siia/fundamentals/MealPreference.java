package siia.fundamentals;

public class MealPreference {

    private Long bookingReference;

    private Long flightReference;

    public Long getBookingReference() {
        return bookingReference;
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
                "bookingReference=" + bookingReference +", " +
                "flightReference=" + flightReference + '}';
    }
}
