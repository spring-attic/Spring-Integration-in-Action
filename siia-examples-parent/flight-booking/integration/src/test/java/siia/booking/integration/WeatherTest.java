package siia.booking.integration;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:weather-endpoint.xml"})
public class WeatherTest {

    private MessagingTemplate channelTemplate;


    @Autowired @Qualifier("weatherRequests")
    public void setChannel(MessageChannel messageChannel){
        this.channelTemplate = new MessagingTemplate(messageChannel);
    }

    @Test
    public void testToronto(){
        // 4118 is the Yahoo! WOEID (Where On Earth ID) for Toronto, Canada
        Message message = MessageBuilder.withPayload("4118").build();
        Message response = channelTemplate.sendAndReceive(message);
        System.out.println(response.getPayload());
    }

}
