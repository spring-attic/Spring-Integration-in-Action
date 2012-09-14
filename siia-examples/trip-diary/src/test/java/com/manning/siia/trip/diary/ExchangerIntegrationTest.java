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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Iwein Fuld
 */
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ExchangerIntegrationTest {

	public static TemporaryFolder parent = new TemporaryFolder();

	private String processId = ManagementFactory.getRuntimeMXBean().getName();

	@Autowired
	@Qualifier("incomingChanges")
	public PollableChannel incomingChanges;

	@Autowired
	@Qualifier("outgoingChanges")
	public MessageChannel outgoingChanges;

	@Autowired
	public FileReadingMessageSource fileReader;

	@BeforeClass
	public static void injectConfig() throws IOException {
		parent.create();
		Config.diary.put("store", parent.getRoot().getPath());
	}

	@Test
	public void shouldParseContext() {
		// this test is be performed by the fixture (Spring)
	}

	@Test
	public void fileReaderShouldGetInputDir() {
		Object inputDir = new DirectFieldAccessor(fileReader).getPropertyValue("directory");
		System.out.println(inputDir);
		assertThat(inputDir, is(notNullValue()));
	}

	@Test(timeout = 10000)
	public void newChangesArePickedUp() throws Exception {
		File file = parent.newFile(Long.toString(System.currentTimeMillis()) + processId);
		System.out.println(file);
		file.createNewFile();
			Object inputDir = new DirectFieldAccessor(fileReader).getPropertyValue("directory");
		System.out.println(inputDir);
		Message<?> received = incomingChanges.receive();
		assertThat(received, is(notNullValue()));
	}

	public static class Config {
		public static final Map<String, String> diary = new HashMap<String, String>();

		public static final String processId = ManagementFactory.getRuntimeMXBean().getName();
	}
}
