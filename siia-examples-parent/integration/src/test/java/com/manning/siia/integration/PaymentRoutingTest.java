package com.manning.siia.integration;

import com.manning.siia.domain.payment.Invoice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Marius Bogoevici
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:payment-routing.xml")
public class PaymentRoutingTest {

    @Autowired @Qualifier("payments")
    MessageChannel payments;

    @Test
    public void testRouting() {

        payments.send(MessageBuilder.withPayload(new Invoice()).build());

    }

}
