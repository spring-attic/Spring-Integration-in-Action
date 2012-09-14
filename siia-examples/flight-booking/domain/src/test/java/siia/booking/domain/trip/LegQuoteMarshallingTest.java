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

import static org.junit.Assert.*;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import siia.booking.domain.Location;
import siia.booking.domain.car.CarCriteria;
import siia.booking.domain.car.CarType;
import siia.booking.domain.flight.FlightCriteria;
import siia.booking.domain.flight.FlightSeatClass;
import siia.booking.domain.hotel.HotelCriteria;
import siia.booking.domain.hotel.RoomType;
import siia.booking.domain.trip.Leg;
import siia.booking.domain.trip.LegQuoteCommand;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author Jonas Partner
 */
public class LegQuoteMarshallingTest {

    Jaxb2Marshaller marshaller;

    LegQuoteCommand exampleLegQuote;

    DateTime startLegDateTime = new DateTime("2010-01-03T00:00:00Z");;

    DateTime endLegDateTime = new DateTime("2010-01-07T00:00:00Z");

    Location london = new Location("UK", "London");
    Location buenosAires = new Location("AR", "Buenos Aires");

    final String exampleQuoteXml = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>" +
            "<legQuote><carCriteria><carType>Compact</carType></carCriteria>" +
            "<flightCriteria><requiredSeatClass>Business</requiredSeatClass>" +
            "<returnRequired>true</returnRequired></flightCriteria>" +
            "<hotelCriteria><roomType>Double</roomType><smokingRoom>false</smokingRoom></hotelCriteria>" +
            "<leg><startOfLegDate>2010-01-03T00:00:00Z</startOfLegDate>" +
            "<endOfLegDate>2010-01-07T00:00:00Z</endOfLegDate>" +
            "<startLocation city='London' countryCode='UK'/>" +
            "<endLocation city='Buenos Aires' countryCode='AR'/>" +
            "</leg></legQuote>";

    @Before
    public void setUp() {
        this.marshaller = new Jaxb2Marshaller();
        this.marshaller.setClassesToBeBound(new Class[]{LegQuoteCommand.class});
        this.exampleLegQuote = new LegQuoteCommand(new Leg(startLegDateTime, endLegDateTime, london, buenosAires));

        CarCriteria carCriteria = new CarCriteria();
        carCriteria.setCarType(CarType.Compact);
        this.exampleLegQuote.setCarCriteria(carCriteria);

        FlightCriteria flightCriteria = new FlightCriteria();
        flightCriteria.setRequiredSeatClass(FlightSeatClass.Business);
        this.exampleLegQuote.setFlightCriteria(flightCriteria);

        HotelCriteria hotelCriteria = new HotelCriteria();
        hotelCriteria.setRoomType(RoomType.Double);
        this.exampleLegQuote.setHotelCriteria(hotelCriteria);
    }

    @Test
    public void testMarshallingLeg() throws Exception {
        StringWriter writer = new StringWriter();
        StreamResult res = new StreamResult(writer);
        marshaller.marshal(this.exampleLegQuote, res);
        assertXMLEqual("Leg quote marshalling incorrect", exampleQuoteXml, writer.getBuffer().toString());
    }

    @Test
    public void testUnmarshallingLeg() throws Exception {
        StreamSource source = new StreamSource(new StringReader(this.exampleQuoteXml));
        Object unmarshalled = marshaller.unmarshal(source);
        assertEquals("Wrong class returned by unmrshalling", LegQuoteCommand.class, unmarshalled.getClass());
        LegQuoteCommand legQuoteReq = (LegQuoteCommand)unmarshalled;
        assertEquals("Wrong leg location", exampleLegQuote.getLeg(), legQuoteReq.getLeg());
    }

}