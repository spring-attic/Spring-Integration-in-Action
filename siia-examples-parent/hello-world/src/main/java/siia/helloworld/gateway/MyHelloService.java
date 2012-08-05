package siia.helloworld.gateway;

public class MyHelloService implements HelloService {

  @Override
  public String sayHello(String name) {
    return "Hello " + name;
  }
}
