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

package com.manning.siia.kitchen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.core.PollableChannel;

/** 
 * @author Iwein Fuld
 */
public class TimedPollableChannel {
	private PollableChannel wrapped;

	public TimedPollableChannel(final PollableChannel wrapped) {
		this.wrapped = wrapped;
	}

	public <T> T receive(long timeout) {
		long millis = System.currentTimeMillis();
		try {
			return (T) wrapped.receive(timeout);
		} finally {
			logger.info("Receive call took: " + (System.currentTimeMillis() - millis) + " millis.");
		}
	}

	public <T> T receive() {
		long millis = System.currentTimeMillis();
		try {
			return (T) wrapped.receive();
		} finally {
			logger.info("Receive call took: " + (System.currentTimeMillis() - millis) + " millis.");
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(TimedPollableChannel.class);
}
