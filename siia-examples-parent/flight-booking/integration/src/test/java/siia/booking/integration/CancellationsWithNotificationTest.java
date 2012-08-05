package siia.booking.integration;

import static org.junit.Assert.assertEquals;
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
