package siia.monitoring.history;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Mark Fisher
 */
public class HistoryDemo1 {

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("context1.xml", HistoryDemo1.class);
	}
}
