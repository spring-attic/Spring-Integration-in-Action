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

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testing the enricher as a simple POJO. No messaging API awareness required.
 * 
 * @author Mark Fisher
 */
public class PassengerProfileEnricherTest {

	@Test
	public void noFrequentFlyerNumberNoProfile() {
		FrequentFlyerService ffService = new StubFrequentFlyerService();
		PassengerProfileEnricher enricher = new PassengerProfileEnricher(ffService);
		Passenger passenger = new Passenger();
		assertNull(passenger.getProfile());
		enricher.addProfileIfAvailable(passenger);
		assertNull(passenger.getProfile());
	}

	@Test
	public void frequentFlyerNumberProfileLookupSucceeds() {
		FrequentFlyerService ffService = new StubFrequentFlyerService();
		PassengerProfileEnricher enricher = new PassengerProfileEnricher(ffService);
		Passenger passenger = new Passenger();
		passenger.setFrequentFlyerNumber("123ABC");
		assertNull(passenger.getProfile());
		enricher.addProfileIfAvailable(passenger);
		assertNotNull(passenger.getProfile());
	}

}
