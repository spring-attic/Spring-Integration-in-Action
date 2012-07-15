package siia.business;

/**
 * @author Marius Bogoevici
 */
public interface FlightStatusNotificationPublisher {
    void publishNotification(FlightStatusEvent event);
}
