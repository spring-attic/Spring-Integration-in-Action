package com.manning.siia.soap;

import siia.booking.domain.Location;
import siia.booking.domain.trip.Leg;
import siia.booking.domain.trip.LegQuoteCommand;
import org.joda.time.DateTime;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceOperations;

/**
 * @author Jonas Partner
 */
public class SoapTripTestClient {

    private static final CommandBuilder builder = new CommandBuilder();


    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("soap-client-applicationContext.xml", SoapTripTestClient.class);
        WebServiceOperations webServiceOperations = (WebServiceOperations) ctx.getBean(WebServiceOperations.class);

        LegQuoteCommand command = new LegQuoteCommand(builder.buildTestLeg());
        Object result = webServiceOperations.marshalSendAndReceive(command);
        System.out.println(result);
    }


    private static class CommandBuilder {

        public Leg buildTestLeg() {
            DateTime now = new DateTime();
            DateTime startOfLeg = now.plusDays(7);
            DateTime endOfLeg = now.plusDays(14);
            Location startLocation = new Location("EN", "London");
            Location endLocation = new Location("US", "New York");
            return new Leg(startOfLeg, endOfLeg, startLocation, endLocation);
        }
    }

}
