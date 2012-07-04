package siia.booking.integration.notifications;

import siia.booking.domain.notifications.FlightNotification;
import siia.booking.domain.notifications.Priority;

import java.util.ArrayList;
import java.util.List;

public class NotificationsRouter {
  public String[] routeNotification(FlightNotification notification) {

    List<String> notificationTypes = new ArrayList<String>();
    if (Priority.HIGH >= (notification.getPriority())) {
      notificationTypes.add("phone");
    }
    if (Priority.MEDIUM >= (notification.getPriority())) {
      notificationTypes.add("sms");
    }
    if (Priority.LOW >= (notification.getPriority())) {
      notificationTypes.add("email");
    }
    return notificationTypes.toArray(new String[0]);
  }
}
