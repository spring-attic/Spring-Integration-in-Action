package siia.monitoring.controlbus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

public class ControlBusDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml", ControlBusDemo.class);
		NumberHolder numberHolder = context.getBean("numberHolder", NumberHolder.class);
		MessageChannel controlChannel = context.getBean("controlChannel", MessageChannel.class);
		System.out.println("value before: " + numberHolder.getNumber());
		Message<String> message = MessageBuilder.withPayload("@numberHolder.increment()").build();
		controlChannel.send(message);
		System.out.println("value after:  " + numberHolder.getNumber());
	}

}
