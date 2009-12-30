package com.manning.siia.domain.hotel;

import java.util.Date;


public class HotelBooking {

    private final Hotel hotel;

    private final Date checkInDate;

    private final Date checkOutDate;

    public HotelBooking(Hotel hotel, Date checkInDate, Date checkOutDate) {
        this.hotel = hotel;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    
}
