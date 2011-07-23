package com.manning.siia.integration.notifications;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marius Bogoevici
 */
public class NotificationsRouter {
    public String[] routeNotification(FlightNotification notification) {

        List<String> notificationTypes = new ArrayList<String>();
        if (Priority.HIGH == (notification.getPriority())) {
            notificationTypes.add("phone");
        }
        if (Priority.MEDIUM == (notification.getPriority())) {
            notificationTypes.add("sms");
        }
        if (Priority.LOW == (notification.getPriority())) {
            notificationTypes.add("email");
        }
        return notificationTypes.toArray(new String[0]);
    }
}
