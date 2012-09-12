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
