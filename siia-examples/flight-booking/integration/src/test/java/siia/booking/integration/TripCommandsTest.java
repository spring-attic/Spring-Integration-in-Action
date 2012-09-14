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

import siia.booking.domain.Command;
import siia.booking.domain.trip.CreateTripCommand;
import siia.booking.domain.trip.Leg;
import siia.booking.domain.trip.Trip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Tests verifying the flow of messages through the system whilst mocking out most business code.
 * 
 * @author Iwein Fuld
 */
@ContextConfiguration("classpath:TEST-trip-commands.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TripCommandsTest {

    @Test
    public void shouldLoadContext() {
        // spring does the job
    }

    //<start id="split-into-subcommands"/>
    @Autowired
    MessageChannel tripCommands;

    @Autowired
    PollableChannel javaLegQuoteCommands;

    @Test
    public void splitterShouldSplitIntoSubcommands() {
        CreateTripCommand tripCommand = mock(CreateTripCommand.class);
        Message<CreateTripCommand> tripCommandMessage = MessageBuilder.withPayload(tripCommand).build();
        final Command carCommand = mock(Command.class);
        final Command flightCommand = mock(Command.class);
        final Command hotelCommand = mock(Command.class);
        given(tripCommand.getSubCommands()).willReturn(Arrays.asList(carCommand, flightCommand, hotelCommand));

        tripCommands.send(tripCommandMessage);
        List<Message<? extends Object>> received =
                Arrays.asList(javaLegQuoteCommands.receive(100), javaLegQuoteCommands.receive(100), javaLegQuoteCommands.receive(100));
        assertThat(received.size(), is(3));
    }
    //<end id="split-into-subcommands"/>

    @Autowired
    MessageChannel legQuotes;

    @Autowired PollableChannel tripQuotes;

    @Test
    public void shouldAggregateQuotedLegsIntoTripSuggestion() {
        Leg fakeLeg1 = mock(Leg.class);
        Leg fakeLeg2 = mock(Leg.class);
        Message<Leg> firstLegMessage =
                createLegMessage(fakeLeg1, 1, 2);
        Message<Leg> secondLegMessage =
                createLegMessage(fakeLeg2, 2, 2);
        legQuotes.send(firstLegMessage);
        legQuotes.send(secondLegMessage);
        Message<?> message = tripQuotes.receive(2000);
        assertThat(message, is(notNullValue()));
        assertThat(((Trip)message.getPayload()).getLegs()
                , hasItems(fakeLeg1, fakeLeg2));
    }

    private Message<Leg> createLegMessage(Leg fakeLeg1,int sequenceNumber, int sequenceSize ) {
        return MessageBuilder.withPayload(fakeLeg1)
                .setCorrelationId("wouldBeSetBySplitter")
                .setSequenceNumber(sequenceNumber)
                .setSequenceSize(sequenceSize)
                .build();
    }
}
