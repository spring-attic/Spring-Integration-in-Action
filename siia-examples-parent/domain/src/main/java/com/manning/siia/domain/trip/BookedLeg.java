package com.manning.siia.domain.trip;

import com.manning.siia.domain.car.CarRentalBooking;
import com.manning.siia.domain.flight.Flight;
import com.manning.siia.domain.flight.FlightBooking;
import com.manning.siia.domain.hotel.HotelBooking;


public class BookedLeg {

    private FlightBooking flightBooking;

    private HotelBooking hotelBooking;

    private CarRentalBooking carRentalBooking;

    private Leg leg;

    public BookedLeg() {
    }

    public BookedLeg(FlightBooking flightBooking, HotelBooking hotelBooking, CarRentalBooking carRentalBooking, Leg leg) {
        this.flightBooking = flightBooking;
        this.hotelBooking = hotelBooking;
        this.carRentalBooking = carRentalBooking;
        this.leg = leg;
    }

    public FlightBooking getFlightBooking() {
        return flightBooking;
    }

    public HotelBooking getHotelBooking() {
        return hotelBooking;
    }

    public CarRentalBooking getCarRentalBooking() {
        return carRentalBooking;
    }

    public Leg getLeg() {
        return leg;
    }
}
