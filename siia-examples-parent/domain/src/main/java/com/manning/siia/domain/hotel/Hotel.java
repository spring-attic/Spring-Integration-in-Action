package com.manning.siia.domain.hotel;

import com.manning.siia.domain.Location;


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
