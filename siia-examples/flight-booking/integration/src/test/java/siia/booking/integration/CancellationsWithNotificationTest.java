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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import siia.booking.domain.cancellation.CancellationConfirmation;
import siia.booking.domain.cancellation.CancellationRequest;

/**
 * @author Marius Bogoevici
 * @author Mark Fisher
 */
@ContextConfiguration("classpath:cancellationsWithNotification.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CancellationsWithNotificationTest {

    @Autowired @Qualifier("input")
    MessageChannel input;

    @Autowired @Qualifier("confirmed")
    PollableChannel confirmed;

    @Autowired
    StubMailSender mailSender;

    @Test
    public void testCancellationsSuccessful() {
        CancellationRequest cancellationRequest = new CancellationRequest();
        cancellationRequest.setReservationCode("GOLD123456");
        input.send(MessageBuilder.withPayload(cancellationRequest).build());
        Message<?> confirmedMessage = confirmed.receive(0);
        assertNotNull(confirmedMessage);
        assertEquals(CancellationConfirmation.class, confirmedMessage.getPayload().getClass());
        CancellationConfirmation confirmation = (CancellationConfirmation) confirmedMessage.getPayload();
        assertEquals("GOLD123456", confirmation.getReservationCode());
    }

    @Test
    public void testCancellationNotification() {
        CancellationRequest cancellationRequest = new CancellationRequest();
        cancellationRequest.setReservationCode("BRONZE123");
        input.send(MessageBuilder.withPayload(cancellationRequest).build());
        Message<?> confirmedMessage = confirmed.receive(0);
        assertNull(confirmedMessage);
        assertEquals(1, mailSender.getCount());
        assertEquals("BRONZE123 has been rejected", mailSender.getLastMessageText());
    }

}
