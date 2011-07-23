package com.manning.siia.integration.cancellation;

/**
 * @author Marius Bogoevici
 */
public class CancellationRequest {

    private String reservationCode;

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getReservationCode() {
        return reservationCode;
    }
}
