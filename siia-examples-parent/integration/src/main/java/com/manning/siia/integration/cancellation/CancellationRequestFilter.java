package com.manning.siia.integration.cancellation;

import org.springframework.integration.annotation.MessageEndpoint;

import java.util.regex.Pattern;

/**
 * @author Marius Bogoevici
 */
@MessageEndpoint
public class CancellationRequestFilter {
    private Pattern pattern;

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public boolean accept(CancellationRequest cancellationRequest) {
        return (cancellationRequest.getReservationCode() != null) &&
                pattern.matcher(cancellationRequest.getReservationCode()).matches();
    }
}
