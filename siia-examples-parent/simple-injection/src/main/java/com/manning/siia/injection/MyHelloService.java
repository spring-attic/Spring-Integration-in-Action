package com.manning.siia.injection;

/**
 * @author Marius Bogoevici
 */
public class MyHelloService implements HelloService {


    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
