package com.manning.siia.coupling;

/**
 * @author Marius Bogoevici
 */
public interface BookingDao {

    Booking getBookingById(Long bookingReference);
}
