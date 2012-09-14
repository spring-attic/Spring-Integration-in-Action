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

package siia.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;

/**
 * @author Mark Fisher
 */
@MessageEndpoint
public class PassengerProfileEnricher {

    private final FrequentFlyerService frequentFlyerService;

    @Autowired
    public PassengerProfileEnricher(FrequentFlyerService ffService) {
        this.frequentFlyerService = ffService;
    }

    public Passenger addProfileIfAvailable(Passenger passenger) {
        String ffNumber = passenger.getFrequentFlyerNumber();
        if (ffNumber != null) {
            Profile profile = this.frequentFlyerService.lookupProfile(ffNumber);
            if (profile != null) {
                passenger.addProfile(profile);
            }
        }
        return passenger;
    }
}