package siia.channels;

/**
 * @author Marius Bogoevici
 */
public class Booking {

    private String flightId;

    private String customerEmail;

    public Booking() {
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
