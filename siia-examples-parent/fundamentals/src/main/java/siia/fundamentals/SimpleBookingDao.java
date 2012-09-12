package siia.fundamentals;

public class SimpleBookingDao implements BookingDao {

  @Override
  public Booking getBookingById(Long bookingReference) {
    return new Booking(1l);
  }
}
