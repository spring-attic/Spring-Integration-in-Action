package com.manning.siia.domain.hotel;


public class Hotel {

    private final long hotelId;

    private final String hotelDescription;

    private final String hotelName;

    public Hotel(long hotelId, String hotelDescription, String hotelName) {
        this.hotelId = hotelId;
        this.hotelDescription = hotelDescription;
        this.hotelName = hotelName;
    }
}
