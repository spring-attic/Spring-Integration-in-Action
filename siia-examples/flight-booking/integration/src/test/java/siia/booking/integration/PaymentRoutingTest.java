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

import siia.booking.domain.payment.CreditCardPayment;
import siia.booking.domain.payment.CreditCardType;
import siia.booking.domain.payment.Invoice;

/**
 * @author Marius Bogoevici
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:payment-routing.xml")
public class PaymentRoutingTest {

    @Autowired @Qualifier("payments")
    MessageChannel payments;

    @Autowired @Qualifier("MASTERCARD")
    PollableChannel mastercardChannel;

    @Test
    public void testRouting() {
        payments.send(MessageBuilder.withPayload(new Invoice()).build());
    }

    @Test
    public void testCreditCardRouting() {
      CreditCardPayment payment = new CreditCardPayment();
      payment.setCreditCardType(CreditCardType.MASTERCARD);
      payments.send(MessageBuilder.withPayload(payment).build());
      Message<?> result = mastercardChannel.receive(0);
      assertNotNull(result);
      assertEquals(CreditCardPayment.class, result.getPayload().getClass());
      assertEquals(payment, result.getPayload());
    }

}
