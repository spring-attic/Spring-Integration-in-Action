package com.manning.siia.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.MessageChannelTemplate;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessageChannel;
import org.springframework.integration.message.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:weather-endpoint.xml"})
public class WeatherTest {

    private MessageChannelTemplate channelTemplate;


    @Autowired @Qualifier("weatherRequests")
    public void setChannel(MessageChannel messageChannel){
        this.channelTemplate = new MessageChannelTemplate(messageChannel);    
    }

    @Test
    public void testCambridge(){
        Message message = MessageBuilder.withPayload("cambridge,uk").build();
        Message response = channelTemplate.sendAndReceive(message);
        System.out.println(response);
    }

}
