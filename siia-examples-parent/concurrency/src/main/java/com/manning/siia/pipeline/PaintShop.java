package com.manning.siia.pipeline;

import org.springframework.integration.annotation.ServiceActivator;

/**
 * @author Marius Bogoevici
 */
public class PaintShop {
    @ServiceActivator
    public FinishedCar assemble(AssembledCar assembledCar) {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            // ignore
        }
        return new FinishedCar(assembledCar.getSerialNumber());
    }
}
