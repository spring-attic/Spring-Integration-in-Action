package siia.jms;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;

public class JmsTemplateDemo {
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory =
				new ActiveMQConnectionFactory("vm://localhost");
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setDefaultDestination(new ActiveMQQueue("siia.queue"));
		jmsTemplate.convertAndSend("hello world");
		System.out.println("received: " + jmsTemplate.receiveAndConvert());
	}
}
