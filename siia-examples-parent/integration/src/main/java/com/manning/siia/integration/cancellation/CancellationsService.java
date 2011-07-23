package com.manning.siia.integration.cancellation;

/**
 * @author Marius Bogoevici
 */
public interface CancellationsService {

    CancellationConfirmation cancel(CancellationRequest cancellationRequest);
}
