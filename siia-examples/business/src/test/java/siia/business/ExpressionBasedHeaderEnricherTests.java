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
import org.springframework.integration.mail.MailHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import siia.business.Passenger;
import siia.business.Profile;

import static org.junit.Assert.assertEquals;

/**
 * @author Marius Bogoevici
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ExpressionBasedHeaderEnricherTests {

    private static final String EMAIL_ADDRESS = "test@example.com";

    @Autowired
    private MessageChannel input;

    @Autowired
    private PollableChannel output;

    @Test
    public void verifyTransformation() {
        Passenger payload = new Passenger();
        Profile profile = new Profile();
        profile.setEmailAddress(EMAIL_ADDRESS);
        payload.addProfile(profile);
        Message<Passenger> passengerToTransform = MessageBuilder.withPayload(payload).build();
        input.send(passengerToTransform);
        Message<Passenger> transformedPassenger = (Message<Passenger>) output.receive(0);
        assertEquals(transformedPassenger.getHeaders().get(MailHeaders.TO), EMAIL_ADDRESS);
    }

}
