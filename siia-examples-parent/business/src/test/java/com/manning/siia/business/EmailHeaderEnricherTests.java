package com.manning.siia.business;

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

import static org.junit.Assert.assertEquals;

/**
 * @author Marius Bogoevici
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class EmailHeaderEnricherTests {

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
