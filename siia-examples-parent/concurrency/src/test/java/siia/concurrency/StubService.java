package siia.concurrency;

import org.springframework.integration.annotation.ServiceActivator;

/**
 * @author Marius Bogoevici
 */
public class StubService<I,O> {

    @ServiceActivator
    public O handle(I input) {
        return null;
    }

}
