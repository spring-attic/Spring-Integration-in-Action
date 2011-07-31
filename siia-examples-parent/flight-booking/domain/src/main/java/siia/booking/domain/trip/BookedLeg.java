package siia.booking.domain.trip;

import siia.booking.domain.car.CarRentalBooking;
import siia.booking.domain.flight.FlightBooking;
import siia.booking.domain.hotel.HotelBooking;


public class BookedLeg {

    private FlightBooking outboundFlightBooking;

    private FlightBooking returnFlightBooking;

    private HotelBooking hotelBooking;

    private CarRentalBooking carRentalBooking;

    private Leg leg;

    public BookedLeg() {
    }

    public BookedLeg(FlightBooking outboundFlightBooking, FlightBooking returnFlightBooking, HotelBooking hotelBooking, CarRentalBooking carRentalBooking, Leg leg) {
        this.outboundFlightBooking = outboundFlightBooking;
        this.returnFlightBooking = returnFlightBooking;
        this.hotelBooking = hotelBooking;
        this.carRentalBooking = carRentalBooking;
        this.leg = leg;
    }

    public BookedLeg(Leg leg){
        this.leg = leg;
    }

    public FlightBooking getOutboundFlightBooking() {
        return outboundFlightBooking;
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

    public void setOutboundFlightBooking(FlightBooking outboundFlightBooking) {
        this.outboundFlightBooking = outboundFlightBooking;
    }

    public void setHotelBooking(HotelBooking hotelBooking) {
        this.hotelBooking = hotelBooking;
    }

    public void setCarRentalBooking(CarRentalBooking carRentalBooking) {
        this.carRentalBooking = carRentalBooking;
    }


    public FlightBooking getReturnFlightBooking() {
        return returnFlightBooking;
    }

    public void setReturnFlightBooking(FlightBooking returnFlightBooking) {
        this.returnFlightBooking = returnFlightBooking;
    }
}
