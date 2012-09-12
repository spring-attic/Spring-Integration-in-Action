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

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

/**
 * @author Mark Fisher
 */
public class DirectJmsDemo {

	public static void main(String[] args) {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
			Connection connection = connectionFactory.createConnection();
			connection.start();
			int autoAck = Session.AUTO_ACKNOWLEDGE;
			Session session = connection.createSession(false, autoAck);
			Destination queue = new ActiveMQQueue("siia.queue.example1");
			MessageProducer producer = session.createProducer(queue);
			MessageConsumer consumer = session.createConsumer(queue);
			Message messageToSend = session.createTextMessage("Hello World");
			producer.send(messageToSend);
			Message receivedMessage = consumer.receive(5000);
			if (!(receivedMessage instanceof TextMessage)) {
				throw new RuntimeException("expected a TextMessage");
			}
			String text = ((TextMessage) receivedMessage).getText();
			System.out.println("received: " + text);
			connection.close();
		}
		catch (JMSException e) {
			throw new RuntimeException("problem occurred in JMS code", e);
		}
	}
}
