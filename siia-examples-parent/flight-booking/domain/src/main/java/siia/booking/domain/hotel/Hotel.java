package siia.booking.domain.hotel;

import siia.booking.domain.Location;

/**
 * @author Jonas Partner
 */
public class Hotel {

    private long hotelId;

    private String hotelDescription;

    private String hotelName;

    private Location location;

    public Hotel(long hotelId, String hotelDescription, String hotelName, Location location) {
        this.hotelId = hotelId;
        this.hotelDescription = hotelDescription;
        this.hotelName = hotelName;
        this.location = location;
    }
}
