package siia.twitter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TimelineToLog {

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("siia/twitter/timeline-to-log.xml");
	}

}
