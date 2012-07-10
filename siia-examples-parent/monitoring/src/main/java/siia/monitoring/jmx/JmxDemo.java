package siia.monitoring.jmx;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

public class JmxDemo {

	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("context.xml", JmxDemo.class);
		MessageChannel channel = context.getBean("channel", MessageChannel.class);
		for (int i = 0; i < 1000; i++) {
			channel.send(MessageBuilder.withPayload(i + "").build());
			Thread.sleep(3000);
		}
	}

}
