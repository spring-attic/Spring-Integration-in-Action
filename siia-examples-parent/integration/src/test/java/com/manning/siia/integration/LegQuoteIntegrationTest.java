package com.manning.siia.integration;

import static org.junit.Assert.*;

import com.manning.siia.domain.Location;
import com.manning.siia.domain.car.CarCriteria;
import com.manning.siia.domain.car.CarType;
import com.manning.siia.domain.flight.FlightCriteria;
import com.manning.siia.domain.flight.FlightSeatClass;
import com.manning.siia.domain.hotel.HotelCriteria;
import com.manning.siia.domain.hotel.RoomType;
import com.manning.siia.domain.trip.Leg;
import com.manning.siia.domain.trip.LegQuoteCommand;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.PollableChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessageChannel;
import org.springframework.integration.message.MessageBuilder;
import org.springframework.oxm.Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.xml.transform.StringResult;
import org.w3c.dom.Document;

import javax.annotation.Resource;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/leg-quote.xml"})
public class LegQuoteIntegrationTest {

    DateTime startLegDateTime = new DateTime("2010-01-03T00:00:00Z");

    DateTime endLegDateTime = new DateTime("2010-01-07T00:00:00Z");

    Location london = new Location("UK", "London");
    Location buenosAires = new Location("AR", "Buenos Aires");

    LegQuoteCommand exampleLegQuote;

    @Resource(name = "javaLegQuoteCommands")
    MessageChannel quoteRequestsChannel;

    @Resource(name = "carQuote")
    PollableChannel carQuotesChannel;

    @Resource(name = "flightQuote")
    PollableChannel flightQuotesChannel;

    @Resource(name = "hotelQuote")
    PollableChannel hotelQuotesChannel;


    @Autowired
    Marshaller marshaller;

    @Before
    public void setUp() {
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
    public void endToEndLegQuoteTest() throws Exception {
        this.quoteRequestsChannel.send(MessageBuilder.withPayload(exampleLegQuote).build(), 1000);

            Message asXml = this.carQuotesChannel.receive(5000);
            assertNotNull(asXml);
            System.out.println(xmlDocToString((Document) asXml.getPayload()));

            asXml = this.flightQuotesChannel.receive(5000);
            System.out.println(asXml);
            System.out.println(xmlDocToString((Document) asXml.getPayload()));

            asXml = this.hotelQuotesChannel.receive(5000);
            System.out.println(asXml);
            System.out.println(xmlDocToString((Document) asXml.getPayload()));

    }

    public String xmlDocToString(Document doc) throws Exception {
        StringResult res = new StringResult();
        TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), res);
        return res.getWriter().toString();
    }

}
