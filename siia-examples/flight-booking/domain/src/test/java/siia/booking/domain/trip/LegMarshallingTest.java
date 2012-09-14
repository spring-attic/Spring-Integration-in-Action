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

import static org.custommonkey.xmlunit.XMLAssert.*;

import siia.booking.domain.Location;
import org.joda.time.DateTime;
import org.joda.time.chrono.ISOChronology;
import org.junit.Before;
import org.junit.Test;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import siia.booking.domain.trip.Leg;

import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * @author Jonas Partner
 */
public class LegMarshallingTest {

    final String marshalledLeg = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>" +
            "<leg><startOfLegDate>1970-01-02T00:00:00Z</startOfLegDate>" +
            "<endOfLegDate>1970-01-03T00:00:00Z</endOfLegDate>" +
            "<startLocation city='London' countryCode='UK'/>" +
            "<endLocation city='New York' countryCode='US'/></leg>";

    Jaxb2Marshaller marshaller;

    @Before
    public void setUp() {
        this.marshaller = new Jaxb2Marshaller();
        this.marshaller.setClassesToBeBound(new Class[]{Leg.class});
    }

    @Test
    public void testMarshallingLeg() throws Exception{
        long day = 24 * 60 * 60 * 1000;
        Leg leg = new Leg(new DateTime(day, ISOChronology.getInstanceUTC()), new DateTime(day * 2, ISOChronology.getInstanceUTC()),
                new Location("UK", "London"), new Location("US", "New York"));

        StringWriter writer = new StringWriter();
        StreamResult res = new StreamResult(writer);
        marshaller.marshal(leg, res);

        assertXMLEqual("Leg marshalling incorrect", marshalledLeg, writer.getBuffer().toString());
    }

}
