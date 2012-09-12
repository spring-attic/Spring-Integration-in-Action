/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package siia.booking.domain.trip;

import siia.booking.domain.Command;
import siia.booking.domain.car.CarCriteria;
import siia.booking.domain.flight.FlightCriteria;
import siia.booking.domain.hotel.HotelCriteria;

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

    /**
     * Private constructor for use by JAXB
     */
    private LegQuoteCommand(){}

    public LegQuoteCommand(Leg leg){
        this.leg = leg;
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
