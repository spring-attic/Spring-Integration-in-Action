package siia.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;

/**
 * @author Marius Bogoevici
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
                return passenger;
            }
        }
        return passenger;
    }
}