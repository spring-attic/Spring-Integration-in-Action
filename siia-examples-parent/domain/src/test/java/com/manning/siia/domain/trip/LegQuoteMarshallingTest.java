package com.manning.siia.domain.trip;

import static org.junit.Assert.*;

import com.manning.siia.domain.Location;
import com.manning.siia.domain.car.CarCriteria;
import com.manning.siia.domain.car.CarType;
import com.manning.siia.domain.flight.FlightCriteria;
import com.manning.siia.domain.flight.FlightSeatClass;
import com.manning.siia.domain.hotel.HotelCriteria;
import com.manning.siia.domain.hotel.RoomType;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

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
        assertEquals("Wrong leg location",exampleLegQuote.getLeg(), legQuoteReq.getLeg()  );

    }


}