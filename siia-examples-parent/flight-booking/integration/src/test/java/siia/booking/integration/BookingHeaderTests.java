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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.integration.test.matcher.HeaderMatcher.hasAllHeaders;
import static org.springframework.integration.test.matcher.HeaderMatcher.hasCorrelationId;
import static org.springframework.integration.test.matcher.HeaderMatcher.hasHeader;
import static org.springframework.integration.test.matcher.HeaderMatcher.hasHeaderKey;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.MessageHeaders;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Mark Fisher
 */
@ContextConfiguration("classpath:bookings.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BookingHeaderTests {

	@Autowired
	@Qualifier("input")
	MessageChannel inputChannel;

	@Autowired
	@Qualifier("output")
	PollableChannel outputChannel;

	private final BookFlightCommand testBookFlightCommand = new BookFlightCommand("SFO", "ORD");

	private Message<?> testMessage() {
		return MessageBuilder.withPayload(testBookFlightCommand).build();
	}

	@Test
	// without header matchers
	public void outputHasOriginalCommandHeader() {
		inputChannel.send(testMessage());
		Message<?> output = outputChannel.receive();
		assertThat((BookFlightCommand) output.getHeaders().get("command"), is(testBookFlightCommand));
	}

	@Test
	// with header matchers
	public void outputHasOriginalCommandHeaderUsingHeaderMatcher() {
		inputChannel.send(testMessage());
		Message<?> message = outputChannel.receive();
		
		assertThat(message, hasHeaderKey("command"));
		assertThat(message, hasHeader("command", notNullValue()));
		assertThat(message, hasHeader("command", is(BookFlightCommand.class)));
		assertThat(message, hasHeader("command", testBookFlightCommand));
	}

	@Test
	public void bookExample() {
		BookFlightCommand testBookFlightCommand =
				new BookFlightCommand("SFO", "ORD");
		Message<?> testMessage =
				MessageBuilder.withPayload(testBookFlightCommand)
					.setCorrelationId("ABC")
					.build();
		inputChannel.send(testMessage);
		Message<?> reply = outputChannel.receive();
		assertThat(reply, hasHeaderKey("command"));
		assertThat(reply, hasHeader("command", notNullValue()));
		assertThat(reply, hasHeader("command", is(BookFlightCommand.class)));
		assertThat(reply, hasHeader("command", testBookFlightCommand));
		assertThat(reply, hasCorrelationId("ABC"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("command", testBookFlightCommand);
		map.put(MessageHeaders.CORRELATION_ID, "ABC");
		assertThat(reply, hasAllHeaders(map));
	}

	/*
	 * other header matching examples 
	 */

	@Test
	public void correlationIdHeaderMatches() {
		Message<?> message = MessageBuilder.withPayload("test").setCorrelationId("ABC-123").build();
		assertThat(message, hasCorrelationId("ABC-123"));
	}

	@Test
	public void headerMapMatches() {
		Message<?> message = MessageBuilder.withPayload("test")
				.setHeader("foo", 123)
				.setHeader("bar", false)
				.build();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("foo", 123);
		map.put("bar", false);
		assertThat(message, hasAllHeaders(map));
	}


	public class BookFlightCommand {

		private final String source;
		private final String destination;

		// just an example... would have other fields as well
		public BookFlightCommand(String source, String destination) {
			this.source = source;
			this.destination = destination;
		}

		public String getSource() {
			return source;
		}

		public String getDestination() {
			return destination;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((destination == null) ? 0 : destination.hashCode());
			result = prime * result
					+ ((source == null) ? 0 : source.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BookFlightCommand other = (BookFlightCommand) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (destination == null) {
				if (other.destination != null)
					return false;
			} else if (!destination.equals(other.destination))
				return false;
			if (source == null) {
				if (other.source != null)
					return false;
			} else if (!source.equals(other.source))
				return false;
			return true;
		}

		private BookingHeaderTests getOuterType() {
			return BookingHeaderTests.this;
		}
	}
}
