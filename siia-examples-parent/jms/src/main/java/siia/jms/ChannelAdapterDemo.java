package siia.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.core.PollableChannel;

/**
 * @author Mark Fisher
 */
public class ChannelAdapterDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("siia/jms/channel-adapters.xml");
		MessageChannel toJMS = context.getBean("toJMS", MessageChannel.class);
		PollableChannel fromJMS = context.getBean("fromJMS", PollableChannel.class);
		MessagingTemplate template = new MessagingTemplate();
		template.convertAndSend(toJMS, "echo");
		Object response = template.receiveAndConvert(fromJMS);
		System.out.println("response: " + response);
	}

}
