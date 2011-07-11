package com.manning.siia.coupling;


/**
 * @author Marius Bogoevici
 */
public class BookingService {

    private final BookingDao bookingDao;

    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    public MealPreference getFlightRefForBooking(MealPreference mealPreference) {
        Booking booking = bookingDao.getBookingById(
                mealPreference.getBookingReference());
        mealPreference.setFlightReference(booking.getFlightRef());
        return mealPreference;
    }
}

