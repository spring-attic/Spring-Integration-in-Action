package com.manning.siia.integration.cancellation;

/**
 * @author Marius Bogoevici
 */
public class StubCancellationsService implements CancellationsService {

    private static final String CONFIRMED = "*CONFIRMED*";

    @Override
    public CancellationConfirmation cancel(CancellationRequest cancellationRequest) {
        //To change body of implemented methods use File | Settings | File Templates.
        CancellationConfirmation cancellationConfirmation = new CancellationConfirmation();
        cancellationConfirmation.setReservationCode(cancellationRequest.getReservationCode());
        cancellationConfirmation.setConfirmationNumber(CONFIRMED);
        return cancellationConfirmation;
    }
}
