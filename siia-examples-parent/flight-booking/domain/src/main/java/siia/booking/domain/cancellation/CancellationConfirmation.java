package siia.booking.domain.cancellation;

/**
 * @author Marius Bogoevici
 */
public class CancellationConfirmation {

    private String reservationCode;

    private String confirmationNumber;

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }
}
