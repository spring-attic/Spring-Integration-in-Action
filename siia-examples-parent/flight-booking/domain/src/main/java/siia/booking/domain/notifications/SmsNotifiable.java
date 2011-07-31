package siia.booking.domain.notifications;

/**
 * @author Iwein Fuld
 */
public class SmsNotifiable implements Notifiable<TripNotification> {
    @Override
    public void notify(TripNotification notification) {
        throw new UnsupportedOperationException(
                "Iwein has left the implementation of this generated method as an exercise to the proverbial reader");
    }
}
