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

package siia.jms;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * @author Mark Fisher
 */
public class MessageListenerContainerDemo {
	public static void main(String[] args) {
		// establish common resources
		ConnectionFactory connectionFactory =
				new ActiveMQConnectionFactory("vm://localhost");
		Destination queue = new ActiveMQQueue("siia.queue");
		// setup and start listener container
		DefaultMessageListenerContainer container =
				new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setDestination(queue);
		container.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				try {
					if (!(message instanceof TextMessage)) {
						throw new IllegalArgumentException("expected TextMessage");
					}
					System.out.println("received: " +
							((TextMessage) message).getText());
				}
				catch (JMSException e) {
					throw new RuntimeException(e);
				}
			}
		});
		container.afterPropertiesSet();
		container.start();
		// send Message
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setDefaultDestination(queue);
		jmsTemplate.convertAndSend("Hello World");
	}
}
