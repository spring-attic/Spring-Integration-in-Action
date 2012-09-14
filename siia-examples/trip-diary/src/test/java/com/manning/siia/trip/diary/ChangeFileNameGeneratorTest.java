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

package com.manning.siia.trip.diary;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.springframework.integration.Message;

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
