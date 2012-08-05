package siia.booking.integration;

import siia.booking.domain.flight.Flight;
import siia.booking.domain.notifications.FlightNotification;
import siia.booking.domain.notifications.SmsNotifiable;
import siia.booking.domain.notifications.TripNotification;
import siia.booking.domain.trip.Trip;
import siia.booking.domain.trip.TripRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * @author Iwein Fuld
 */
@ContextConfiguration("classpath:TEST-flight-notifications-header-enricher-bean.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightNotificationsWithHeaderEnricherBeanTest {

    @Test
    public void shouldLoadContext() {
        //spring does the job
    }

    // Used in chapter 8 (splitters, resequencers and aggregators)
    @Autowired
    MessageChannel flightNotifications;

    @Autowired
    PollableChannel interceptedTripNotifications;

    @Autowired
    TripRepository mockedTripRepository;

    @Test
    public void shouldEnrichHeaderAndSendMessageForRelatedTrips() {
        final Flight flight = mock(Flight.class);
        final FlightNotification notification = new FlightNotification("Flight has been cancelled", flight);
        final Message<FlightNotification> testMessage = MessageBuilder.withPayload(notification).build();
        Trip trip1 = mock(Trip.class);
        Trip trip2 = mock(Trip.class);
        List<Trip> relatedTrips = asList(trip1, trip2);
        given(mockedTripRepository.findTripsRelatedTo(flight)).willReturn(relatedTrips);
        flightNotifications.send(testMessage);
        Message<TripNotification> tripNotificationMessage1 =
                (Message<TripNotification>) interceptedTripNotifications.receive(1000);
        assertThat(tripNotificationMessage1, is(notNullValue()));
        assertThat((List<Trip>) tripNotificationMessage1.getHeaders().get("affectedTrips"), is(relatedTrips));
        assertThat(tripNotificationMessage1.getPayload().getTrip(), is(sameInstance(trip1)));
        Message<TripNotification> tripNotificationMessage2 =
                (Message<TripNotification>) interceptedTripNotifications.receive(1000);
        assertThat(tripNotificationMessage2.getPayload().getTrip(), is(sameInstance(trip2)));
    }

    @Autowired
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

}
