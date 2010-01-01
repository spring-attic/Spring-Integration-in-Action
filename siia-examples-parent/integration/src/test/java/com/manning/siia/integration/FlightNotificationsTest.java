package com.manning.siia.integration;

import com.manning.siia.integration.notifications.SmsNotifiable;
import com.manning.siia.integration.notifications.TripNotification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessageChannel;
import org.springframework.integration.message.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 */
@ContextConfiguration("classpath:TEST-flight-notifications.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightNotificationsTest {

    @Test
    public void shouldLoadContext() {
        //spring does the job
    }

    /* <start id="trip-notifications-to-sms-notifier-test"/> */
    @Autowired
    @Qualifier("trip-notifications")
    MessageChannel tripNotifications;

    @Autowired
    SmsNotifiable smsNotifier;

    @Test
    public void notificationShouldArriveAtSmsAdapter() throws Exception {
        TripNotification notification = mock(TripNotification.class);
        Message tripNotificationMessage =
                MessageBuilder
                        .withPayload(notification)
                        .build();
        CountDownLatch notifierInvoked = new CountDownLatch(1);
        doAnswer(countsDownLatch(notifierInvoked))
                .when(smsNotifier).notify(notification);
        tripNotifications.send(tripNotificationMessage);
        notifierInvoked.await(100, MILLISECONDS);
        verify(smsNotifier).notify(notification);
    }
    /* <end id="trip-notifications-to-sms-notifier-test"/> */
    
    /* <start id="counts-down-latch"/> */
    private Answer countsDownLatch(final CountDownLatch notifierInvoked) {
        return new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock)
                    throws Throwable {
                notifierInvoked.countDown();
                return null;
            }
        };
    }
    /* <end id="counts-down-latch"/> */
}
