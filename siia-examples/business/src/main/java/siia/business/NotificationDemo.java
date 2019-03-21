/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
