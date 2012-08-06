package com.manning.siia.pipeline;

import org.springframework.integration.annotation.ServiceActivator;

/**
 * @author Marius Bogoevici
 */
public class AssemblyLine {

    @ServiceActivator
    public AssembledCar assemble(PieceKit pieceKit) {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            // ignore
        }
        return new AssembledCar(pieceKit.getSerialNumber());
    }

}
