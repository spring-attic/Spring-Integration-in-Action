package com.manning.siia.domain.trip;

import com.manning.siia.domain.Command;
import com.manning.siia.domain.car.CarCriteria;
import com.manning.siia.domain.flight.FlightCriteria;
import com.manning.siia.domain.hotel.HotelCriteria;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jonas Partner
 */
@XmlRootElement(name = "legQuote")
public class LegQuoteCommand implements Command {

    private Leg leg;

    private HotelCriteria hotelCriteria;

    private FlightCriteria flightCriteria;

    private CarCriteria carCriteria;

    private LegQuoteCommand(){}

    public LegQuoteCommand(Leg leg){
        this.leg= leg;
    }

    public Leg getLeg() {
        return leg;
    }

    public void setLeg(Leg leg) {
        this.leg = leg;
    }

    public HotelCriteria getHotelCriteria() {
        return hotelCriteria;
    }

    public void setHotelCriteria(HotelCriteria hotelCriteria) {
        this.hotelCriteria = hotelCriteria;
    }

    public FlightCriteria getFlightCriteria() {
        return flightCriteria;
    }

    public void setFlightCriteria(FlightCriteria flightCriteria) {
        this.flightCriteria = flightCriteria;
    }

    public CarCriteria getCarCriteria() {
        return carCriteria;
    }

    public void setCarCriteria(CarCriteria carCriteria) {
        this.carCriteria = carCriteria;
    }
}
