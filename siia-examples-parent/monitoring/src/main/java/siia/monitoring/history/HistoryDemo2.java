package siia.monitoring.history;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HistoryDemo2 {

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("context2.xml", HistoryDemo2.class);
	}
}
