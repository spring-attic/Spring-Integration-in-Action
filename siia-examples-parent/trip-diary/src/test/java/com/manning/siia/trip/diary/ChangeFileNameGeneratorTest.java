package com.manning.siia.trip.diary;

import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Mockito.mock;
import org.springframework.integration.core.Message;

import java.lang.management.ManagementFactory;

/**
 * @author Iwein Fuld
 */
public class ChangeFileNameGeneratorTest {

    private String processId = ManagementFactory.getRuntimeMXBean().getName();
    private final ChangeFileNameGenerator generator =
            new ChangeFileNameGenerator(processId);

    @Test
    public void processIdShouldNotBeNull() {
        assertThat(processId, containsString("@"));
    }

    @Test
    public void filenameShouldContainProcessId() {
        Message<?> message = mock(Message.class);
        assertThat(generator.generateFileName(message), containsString(processId));
    }

    @Test
    public void filenameShouldContainTodaysDate() {
        Message<?> message = mock(Message.class);
        assertThat(
                generator.generateFileName(message),
                containsString(Long.toString(System.currentTimeMillis()))
        );
    }
}
