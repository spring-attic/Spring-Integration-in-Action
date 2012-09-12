/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package siia.channels;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessageHandler;

/**
 * @author Marius Bogoevici
 */
public class StubEmailConfirmationService implements MessageHandler {

    private List<Email> emails = new ArrayList<Email>();

    CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {

        SeatConfirmation seatConfirmation = (SeatConfirmation) message.getPayload();

        ChargedBooking chargedBooking = seatConfirmation.getChargedBooking();
        Booking booking = chargedBooking.getBooking();
        Email email = new Email(booking.getCustomerEmail(),
                "Your booking on flight " + booking.getFlightId() + " has been confirmed." +
                "You have seat number " + seatConfirmation.getSeat().getSeatNumber());

        sendEmail(email);
    }

    // we don't send an e-mail, but an integration service actually would
    public void sendEmail(Email email) {
        countDownLatch.countDown();
        emails.add(email);
    }

    public List<Email> getEmails() throws InterruptedException {
        countDownLatch.await(5, TimeUnit.SECONDS);
        return emails;
    }
}
