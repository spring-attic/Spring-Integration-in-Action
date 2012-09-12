package siia.business;

/**
 * @author Mark Fisher
 */
public interface FlightStatusService {
    FlightStatus updateStatus(FlightDelayEvent flightDelayEvent);
}

