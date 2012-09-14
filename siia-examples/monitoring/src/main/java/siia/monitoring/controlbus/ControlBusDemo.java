/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package siia.monitoring.controlbus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Mark Fisher
 */
public class ControlBusDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml", ControlBusDemo.class);
		NumberHolder numberHolder = context.getBean("numberHolder", NumberHolder.class);
		MessageChannel controlChannel = context.getBean("controlChannel", MessageChannel.class);
		System.out.println("number before increment: " + numberHolder.getNumber());
		Message<String> message = MessageBuilder.withPayload("@numberHolder.increment()").build();
		controlChannel.send(message);
		System.out.println("number after increment:  " + numberHolder.getNumber());
		FilePoller filePoller = context.getBean("filePoller", FilePoller.class);
		System.out.println("file poller isRunning before start: " + filePoller.isRunning());
		controlChannel.send(MessageBuilder.withPayload("@filePoller.start()").build());
		System.out.println("file poller isRunning after start:  " + filePoller.isRunning());
		controlChannel.send(MessageBuilder.withPayload("@filePoller.stop()").build());
		System.out.println("file poller isRunning after stop:   " + filePoller.isRunning());
		ThreadPoolTaskExecutor executor = context.getBean("myExecutor", ThreadPoolTaskExecutor.class);
		System.out.println("max pool size before update: " + executor.getMaxPoolSize());
		controlChannel.send(MessageBuilder.withPayload("@myExecutor.setMaxPoolSize(25)").build());
		System.out.println("max pool size after update:  " + executor.getMaxPoolSize());
	}

}
