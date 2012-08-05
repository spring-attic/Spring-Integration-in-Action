package siia.business;

import org.springframework.integration.annotation.Payload;
import org.springframework.integration.annotation.Publisher;
import org.springframework.stereotype.Component;

/**
 * @author Marius Bogoevici
 */
@Component
public class SimpleFlightStatusService implements FlightStatusService {

    @Publisher(channel = "statisticsChannel")
    public FlightStatus updateStatus(@Payload FlightDelayEvent flightDelayEvent) {
        // update flight status information in the database
        // return updated status
    	return new FlightStatus();
    }
}
