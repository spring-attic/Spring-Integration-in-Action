package siia.business;

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
@ContextConfiguration(locations = {"classpath:chain.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ChainTests {

    @Autowired @Qualifier("passengers")
    MessageChannel passengerChannel;

    @Autowired
    StubMailSender mailSender;

    @Test
    public void testChain() {
        Passenger passenger = new Passenger();
        passenger.setFrequentFlyerNumber("123");
        Message<Passenger> passengerMessage = MessageBuilder.withPayload(passenger).build();
        passengerChannel.send(passengerMessage);
        Assert.assertEquals(1, mailSender.getCount());
        Assert.assertEquals("Your flight has been delayed", mailSender.getLastMessageText());
    }
}
