package com.manning.siia.soap;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceOperations;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

public class SoapTripTestClient {

    public static void main(String[] args){
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("soap-client-applicationContext.xml", SoapTripTestClient.class);
        WebServiceOperations webServiceOperations = (WebServiceOperations)ctx.getBean(WebServiceOperations.class);
        StringSource testRequest = new StringSource("<test/>");

        StringResult testResult = new StringResult();
        webServiceOperations.sendSourceAndReceiveToResult(testRequest, testResult);
        System.out.println(testResult);
    }

}
