package com.manning.siia.pipeline;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.integration.annotation.ServiceActivator;

/**
 * @author Marius Bogoevici
 */
public class Counter {

   private int count = 0;

   private CountDownLatch latch = new CountDownLatch(1);

    public synchronized void resetLatch(int latchSize) {
       latch = new CountDownLatch(latchSize);
    }

    @ServiceActivator
    public void count(FinishedCar assembledCar) {
       count++;
       latch.countDown();
       System.out.println("Assembled car " + assembledCar.getSerialNumber());
    }

    public int getCount() throws InterruptedException {
        latch.await(30, TimeUnit.SECONDS);
        return count;
    }
}
