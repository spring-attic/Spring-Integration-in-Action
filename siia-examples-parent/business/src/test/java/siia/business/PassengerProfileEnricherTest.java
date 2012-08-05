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
