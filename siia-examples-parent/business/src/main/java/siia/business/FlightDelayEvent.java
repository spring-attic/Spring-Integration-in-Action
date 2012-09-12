package siia.business;

import java.util.Date;

/**
 * @author Mark Fisher
 */
public class FlightDelayEvent {
    private Flight flight;
    private Date estimatedDeparture;

    public FlightDelayEvent(Flight flight, Date time) {
        this.flight = flight;
        this.estimatedDeparture = time;
    }

    public Date getEstimatedDeparture() {
        return estimatedDeparture;
    }
}
