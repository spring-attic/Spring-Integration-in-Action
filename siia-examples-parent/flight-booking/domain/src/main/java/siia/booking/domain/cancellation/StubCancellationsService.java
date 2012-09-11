package siia.booking.domain.cancellation;

/**
 * @author Marius Bogoevici
 */
public class StubCancellationsService implements CancellationsService {

    private static final String CONFIRMED = "*CONFIRMED*";

    @Override
    public CancellationConfirmation cancel(CancellationRequest cancellationRequest) {
        CancellationConfirmation cancellationConfirmation = new CancellationConfirmation();
        cancellationConfirmation.setReservationCode(cancellationRequest.getReservationCode());
        cancellationConfirmation.setConfirmationNumber(CONFIRMED);
        return cancellationConfirmation;
    }
}
