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
