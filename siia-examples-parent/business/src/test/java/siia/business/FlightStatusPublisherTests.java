package siia.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.core.PollableChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Mark Fisher
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightStatusPublisherTests {

    @Autowired
    private FlightStatusService flightStatusService;

    @Autowired
    private PollableChannel statisticsChannel;

    @Test
    public void verifyPublish() {
    	Flight flight = new Flight("ABC123");
    	Date time = new Date();
    	FlightDelayEvent flightDelayEvent = new FlightDelayEvent(flight, time);
    	flightStatusService.updateStatus(flightDelayEvent);
    	Message<?> published = statisticsChannel.receive(0);
    	assertNotNull(published);
    	assertEquals(FlightDelayEvent.class, published.getPayload().getClass());
    	assertSame(flightDelayEvent, published.getPayload());
    }

}
