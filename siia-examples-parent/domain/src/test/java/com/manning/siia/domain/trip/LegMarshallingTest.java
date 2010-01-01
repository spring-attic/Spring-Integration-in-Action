package com.manning.siia.domain.trip;

import static org.custommonkey.xmlunit.XMLAssert.*;

import com.manning.siia.domain.Location;
import com.manning.siia.domain.binding.JodaDateTimeBinder;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Date;


public class LegMarshallingTest {

    final String marshalledLeg = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>" +
            "<leg><startOfLegDate>1970-01-02T01:00:00+01:00</startOfLegDate>" +
            "<endOfLegDate>1970-01-03T01:00:00+01:00</endOfLegDate>" +
            "<startLocation city='London' countryCode='UK'/>" +
            "<endLocation city='New York' countryCode='US'/></leg>";

    Jaxb2Marshaller marshaller;

    @Before
    public void setUp() {
        this.marshaller = new Jaxb2Marshaller();
        this.marshaller.setClassesToBeBound(new Class[]{Leg.class});
        this.marshaller.setAdapters(new XmlAdapter[]{new JodaDateTimeBinder()});
    }

    @Test
    public void testMarshallingLeg() throws Exception{
        long day = 24 * 60 * 60 * 1000;
        Leg leg = new Leg(new DateTime(day), new DateTime(day * 2),
                new Location("UK", "London"), new Location("US", "New York"));

        StringWriter writer = new StringWriter();
        StreamResult res = new StreamResult(writer);
        marshaller.marshal(leg, res);

        assertXMLEqual("Leg marshalling incorrect",marshalledLeg, writer.getBuffer().toString());
    }

}
