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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Mark Fisher
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightDelayTransformerIntegrationTests {

    @Autowired
    private MessageChannel flightDelayInput;

    @Autowired
    private PollableChannel delayEvents;

    @Test
    public void verifyDelay() {
        long currentTime = System.currentTimeMillis();
        Message<String> message =
                MessageBuilder.withPayload("SI77+0130").build();
        flightDelayInput.send(message);
        Message<?> transformed = delayEvents.receive(0);
        assertNotNull(transformed);
        Object payload = transformed.getPayload();
        assertNotNull(payload);
        assertEquals(FlightDelayEvent.class, payload.getClass());
        Date estimatedDeparture =
                ((FlightDelayEvent) payload).getEstimatedDeparture();
        long secondsFor1hr30min = 90 * 60;
        long delay = estimatedDeparture.getTime() - currentTime;
        assertEquals(secondsFor1hr30min, delay / 1000);
    }
}
