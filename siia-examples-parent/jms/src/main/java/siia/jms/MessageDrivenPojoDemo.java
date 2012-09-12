package siia.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author Mark Fisher
 */
public class MessageDrivenPojoDemo {

	public static void main(String[] args) {
		// start the context that contains the message-driven POJO
		ApplicationContext context = new ClassPathXmlApplicationContext("siia/jms/message-driven-pojo.xml");

		// send a Message with JmsTemplate
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		jmsTemplate.convertAndSend("siia.mdp.queue", "World");
	}

}
