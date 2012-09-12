package siia.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.util.Assert;

import java.util.Calendar;

/**
 * @author Mark Fisher
 */
@MessageEndpoint
public class FlightEventTransformer {
  private final FlightScheduler flightScheduler;

  @Autowired
  public FlightEventTransformer(FlightScheduler flightScheduler) {
    Assert.notNull(flightScheduler,
        "flightScheduler must not be null");
    this.flightScheduler = flightScheduler;
  }

  public FlightDelayEvent convertToDelayEvent(
      String flightNumberAndDelay) {
    String[] splits = flightNumberAndDelay.split("[+]");
    Flight flight =
        this.flightScheduler.nextFlightForNumber(splits[0]);
    int hours = Integer.parseInt(splits[1].substring(0, 2));
    int minutes = Integer.parseInt(splits[1].substring(2));
    Calendar cal = Calendar.getInstance();
    cal.setTime(flight.getScheduledDeparture());
    cal.add(Calendar.HOUR, hours);
    cal.add(Calendar.MINUTE, minutes);
    return new FlightDelayEvent(flight, cal.getTime());
  }
}