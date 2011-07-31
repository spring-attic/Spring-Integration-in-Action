package siia.booking.integration;

import siia.booking.domain.cancellation.CancellationConfirmation;
import siia.booking.domain.cancellation.CancellationRequest;
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

/**
 * @author Marius Bogoevici
 */
@ContextConfiguration("classpath:cancellations.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CancellationsTest {

    @Autowired @Qualifier("input")
    MessageChannel input;

    @Autowired @Qualifier("confirmed")
    PollableChannel confirmed;

    @Autowired @Qualifier("rejected")
    PollableChannel rejected;

    @Test
    public void testCancellations() {
        CancellationRequest cancellationRequest = new CancellationRequest();
        cancellationRequest.setReservationCode("GOLD1");
        input.send(MessageBuilder.withPayload(cancellationRequest).build());

        Message<CancellationConfirmation> confirmedMessage
                = (Message<CancellationConfirmation>) confirmed.receive(0);


    }
}
