package siia.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.core.PollableChannel;

public class GatewayDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("siia/jms/gateways.xml");
		MessageChannel toJMS = context.getBean("toJMS", MessageChannel.class);
		PollableChannel jmsReplies = context.getBean("jmsReplies", PollableChannel.class);
		MessagingTemplate template = new MessagingTemplate();
		template.convertAndSend(toJMS, "echo");
		Object response = template.receiveAndConvert(jmsReplies);
		System.out.println("response: " + response);
	}

}
