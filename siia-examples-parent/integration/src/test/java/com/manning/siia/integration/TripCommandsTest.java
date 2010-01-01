package com.manning.siia.integration;

import com.manning.siia.domain.Command;
import com.manning.siia.domain.CreateTripCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.PollableChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessageChannel;
import org.springframework.integration.message.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Tests verifying the flow of messages through the system whilst mocking out most business code.
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
    @Qualifier("tripCommands")
    MessageChannel tripCommands;

    @Autowired
    @Qualifier("subCommands")
    PollableChannel subCommands;

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
                Arrays.asList(subCommands.receive(100), subCommands.receive(100), subCommands.receive(100));
        assertThat(received.size(), is(3));
       // assertThat(received, hasItems(hasPayload(carCommand), hasPayload(flightCommand), hasPayload(hotelCommand)));
    }
    //<end id="split-into-subcommands"/>

}
