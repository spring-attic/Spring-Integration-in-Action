package siia.monitoring.wiretap;

import java.math.BigDecimal;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

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
