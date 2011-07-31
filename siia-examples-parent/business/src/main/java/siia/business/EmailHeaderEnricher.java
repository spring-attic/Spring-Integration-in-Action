package siia.business;

import org.springframework.integration.Message;
import org.springframework.integration.annotation.Headers;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.mail.MailHeaders;
import org.springframework.integration.support.MessageBuilder;

import java.util.Map;

/**
 * @author Marius Bogoevici
 */
@MessageEndpoint
public class EmailHeaderEnricher {

    public Message<Passenger> populateEmailHeader(Passenger passenger, @Headers Map<String, Object> headers) {

        MessageBuilder<Passenger> responseBuilder = MessageBuilder.withPayload(passenger).copyHeaders(headers);
        Profile profile = passenger.getProfile();
        if (profile != null) {
            String emailAddress = profile.getEmailAddress();
            if (emailAddress != null) {
                responseBuilder.setHeader(MailHeaders.TO, emailAddress);
            }

        }
        return responseBuilder.build();
    }
}

