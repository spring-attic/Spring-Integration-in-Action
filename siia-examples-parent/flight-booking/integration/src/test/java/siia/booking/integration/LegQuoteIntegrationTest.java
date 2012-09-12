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

package siia.booking.integration;

import static org.junit.Assert.*;

import siia.booking.domain.Location;
import siia.booking.domain.car.CarCriteria;
import siia.booking.domain.car.CarType;
import siia.booking.domain.flight.FlightCriteria;
import siia.booking.domain.flight.FlightSeatClass;
import siia.booking.domain.hotel.HotelCriteria;
import siia.booking.domain.hotel.RoomType;
import siia.booking.domain.trip.Leg;
import siia.booking.domain.trip.LegQuoteCommand;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.oxm.Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.xml.transform.StringResult;
import org.w3c.dom.Document;

import javax.annotation.Resource;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;

/**
 * @author Jonas Partner
 */
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

    @Resource(name = "carQuoteChannel")
    PollableChannel carQuotesChannel;

    @Resource(name = "validFlightQuoteChannel")
    PollableChannel flightQuotesChannel;

    @Resource(name = "hotelQuoteChannel")
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
