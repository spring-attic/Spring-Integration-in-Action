package com.manning.siia.integration.notifications;

/**
 *
 * @author Iwein Fuld
 */
public interface Notifiable<T> {
    void notify(T notification);
}
