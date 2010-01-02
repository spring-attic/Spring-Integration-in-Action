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
import com.manning.siia.domain.trip.LegQuoteRequest;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.integration.channel.MessageChannelTemplate;
import org.springframework.integration.channel.PollableChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessageChannel;
import org.springframework.integration.message.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/leg-quote.xml"})
public class LegQuoteIntegrationTest {

    DateTime startLegDateTime = new DateTime("2010-01-03T00:00:00Z");
    
    DateTime endLegDateTime = new DateTime("2010-01-07T00:00:00Z");

    Location london = new Location("UK", "London");
    Location buenosAires = new Location("AR", "Buenos Aires");

    LegQuoteRequest exampleLegQuote;

    @Resource(name = "legQuoteRequests")
    MessageChannel quoteRequestsChannel;

    @Resource(name = "legQuotes")
    PollableChannel quoteChannel;

    @Before
    public void setUp() {
        this.exampleLegQuote = new LegQuoteRequest(new Leg(startLegDateTime, endLegDateTime, london, buenosAires));

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
    public void endToEndLegQuoteTest() {
       this.quoteRequestsChannel.send(MessageBuilder.withPayload(exampleLegQuote).build(), 1000);
        Message asXml = this.quoteChannel.receive();
        assertNotNull(asXml);
    }

}
