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

package siia.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.core.PollableChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Mark Fisher
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightStatusPublisherTests {

    @Autowired
    private FlightStatusService flightStatusService;

    @Autowired
    private PollableChannel statisticsChannel;

    @Test
    public void verifyPublish() {
    	Flight flight = new Flight("ABC123");
    	Date time = new Date();
    	FlightDelayEvent flightDelayEvent = new FlightDelayEvent(flight, time);
    	flightStatusService.updateStatus(flightDelayEvent);
    	Message<?> published = statisticsChannel.receive(0);
    	assertNotNull(published);
    	assertEquals(FlightDelayEvent.class, published.getPayload().getClass());
    	assertSame(flightDelayEvent, published.getPayload());
    }

}
