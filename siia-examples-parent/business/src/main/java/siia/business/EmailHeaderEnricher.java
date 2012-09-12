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

package siia.business;

import org.springframework.integration.Message;
import org.springframework.integration.annotation.Headers;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.mail.MailHeaders;
import org.springframework.integration.support.MessageBuilder;

import java.util.Map;

/**
 * @author Mark Fisher
 */
@MessageEndpoint
public class EmailHeaderEnricher {

    public Message<Passenger> populateEmailHeader(Passenger passenger,
                               @Headers Map<String, Object> headers) {

        MessageBuilder<Passenger> responseBuilder =
            MessageBuilder.withPayload(passenger).copyHeaders(headers);
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

