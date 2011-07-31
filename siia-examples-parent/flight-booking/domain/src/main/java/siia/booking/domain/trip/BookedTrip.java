package siia.booking.domain.trip;

import siia.booking.domain.user.User;

import java.util.List;

/**
 * @author Jonas Partner
 */
public class BookedTrip {

    private final User user;

    private final List<BookedLeg> bookedLegs;

    public BookedTrip(List<BookedLeg> bookedLegs, User user) {
        this.user = user;
        this.bookedLegs = bookedLegs;
    }

    public List<BookedLeg> getLegs() {
        return bookedLegs;
    }
}
