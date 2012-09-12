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
