package siia.helloworld.gateway;

/**
 * @author Mark Fisher
 */
public class MyHelloService implements HelloService {

  @Override
  public String sayHello(String name) {
    return "Hello " + name;
  }
}
