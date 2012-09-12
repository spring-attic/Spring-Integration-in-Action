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

package siia.channels;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Marius Bogoevici
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:channels-all-direct.xml")
public class AllChannelsDirectTest {

    @Autowired
    @Qualifier("bookingConfirmationRequests")
    MessageChannel bookingsChannel;

    @Autowired
    StubEmailConfirmationService emailConfirmationService;

    @Test
    public void testChannels() throws Exception {
        Booking booking = new Booking();
        booking.setCustomerEmail("user@example.com");
        booking.setFlightId("AC100");
        Message<Booking> bookingMessage = MessageBuilder.withPayload(booking).build();
        bookingsChannel.send(bookingMessage);

        Assert.assertEquals(1, emailConfirmationService.getEmails().size());
        Assert.assertEquals("user@example.com", emailConfirmationService.getEmails().get(0).getRecipient());
    }
}
