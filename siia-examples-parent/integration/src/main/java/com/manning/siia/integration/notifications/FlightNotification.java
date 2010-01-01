package com.manning.siia.integration.notifications;

/**
 *
 * @author Iwein Fuld
 */
public class FlightNotification {
    private final String message;

    public FlightNotification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
