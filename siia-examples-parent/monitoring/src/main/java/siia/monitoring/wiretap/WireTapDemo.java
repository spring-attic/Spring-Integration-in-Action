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

package siia.monitoring.wiretap;

import java.math.BigDecimal;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

/**
 * @author Mark Fisher
 */
public class WireTapDemo {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("context.xml", WireTapDemo.class);
		MessageChannel debitChannel =
				context.getBean("debitChannel", MessageChannel.class);
		Message<Debit> message1 = MessageBuilder.withPayload(
				new Debit(new BigDecimal(5000), "SMALL")).build();
		Message<Debit> message2 = MessageBuilder.withPayload(
				new Debit(new BigDecimal(25000), "BIG")).build();
		debitChannel.send(message1);
		debitChannel.send(message2);
	}

}
