package siia.channels;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Marius Bogoevici
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:channels-priority.xml")
public class ChannelsPriorityTest {

    @Autowired
    @Qualifier("bookingConfirmationRequests")
    MessageChannel bookingsChannel;

    @Autowired
    StubEmailConfirmationService emailConfirmationService;

    @Autowired
    ConfigurableApplicationContext applicationContext;

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
