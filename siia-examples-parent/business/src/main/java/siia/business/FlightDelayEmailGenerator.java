package siia.business;

import org.springframework.stereotype.Component;

/**
 * @author Marius Bogoevici
 */
@Component
public class FlightDelayEmailGenerator {

    public String generateEmail(Passenger passenger) {
        return "Your flight has been delayed";
    }
}
