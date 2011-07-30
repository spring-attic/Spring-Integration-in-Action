package com.manning.siia.injection;

public class MyHelloService implements HelloService {


    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
