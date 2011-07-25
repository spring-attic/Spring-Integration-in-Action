package com.manning.siia.kitchen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.core.PollableChannel;

/**
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
