package com.manning.siia.coupling;

/**
 * @author Marius Bogoevici
 */
public class MockBookingDao implements BookingDao {

    @Override
    public Booking getBookingById(Long bookingReference) {
        return new Booking(1l);
    }
}
