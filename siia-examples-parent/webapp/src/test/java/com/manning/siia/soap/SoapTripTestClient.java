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
