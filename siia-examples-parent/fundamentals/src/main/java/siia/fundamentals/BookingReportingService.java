package siia.fundamentals;

/**
 * @author Marius Bogoevici
 */
public class BookingReportingService {

    private final BookingDao bookingDao;

    public BookingReportingService(){
       this.bookingDao = new SimpleBookingDao();
    }
    /** Actual methods omitted */
}