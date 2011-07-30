package siia.helloworld.gateway;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class HelloWorldExample {

  public static void main(String args[]) {
    ApplicationContext context =
      new ClassPathXmlApplicationContext("siia/helloworld/gateway/context.xml");
    HelloService helloService =
      context.getBean("helloGateway", HelloService.class);
    System.out.println(helloService.sayHello("World"));
  }

}