package siia.booking.integration.cancellation;

import org.springframework.integration.annotation.MessageEndpoint;
import siia.booking.domain.cancellation.CancellationRequest;

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
    	String code = cancellationRequest.getReservationCode();
    	return code != null && pattern.matcher(code).matches();
    }
}
