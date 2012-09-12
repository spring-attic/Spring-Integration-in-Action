package siia.business;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Mark Fisher
 */
public class NotificationDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("notificationPublisher.xml");
		FlightStatusNotificationPublisher publisher = context.getBean(FlightStatusNotificationPublisher.class);
		FlightStatusEvent event = new FlightStatusEvent();
		publisher.publishNotification(event);
	}

}
