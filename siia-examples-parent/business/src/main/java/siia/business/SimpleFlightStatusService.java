package siia.business;

import org.springframework.integration.annotation.Payload;
import org.springframework.integration.annotation.Publisher;

/**
 * @author Marius Bogoevici
 */
public class SimpleFlightStatusService implements FlightStatusService {

    @Publisher(channel = "statisticsChannel")
    public FlightStatus updateStatus(@Payload FlightDelayEvent flightDelayEvent) {
        // update status
    	return new FlightStatus();
    }
}
