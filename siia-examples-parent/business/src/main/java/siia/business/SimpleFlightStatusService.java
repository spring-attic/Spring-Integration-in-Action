package siia.business;


import org.springframework.integration.annotation.Payload;
import org.springframework.integration.annotation.Publisher;

/**
 * @author Marius Bogoevici
 */
public class SimpleFlightStatusService implements FlightStatusService {

    @Override
    @Publisher(channel = "statisticsChannel")
    public void updateStatus(@Payload FlightDelayEvent
                             flightDelayEvent) {
        // update status
    }
}
