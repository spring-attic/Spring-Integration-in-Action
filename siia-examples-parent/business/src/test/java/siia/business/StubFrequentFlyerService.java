package siia.business;

import siia.business.FrequentFlyerService;
import siia.business.Profile;

/**
 * @author Marius Bogoevici
 */
public class StubFrequentFlyerService implements FrequentFlyerService {

    @Override
    public Profile lookupProfile(String frequentFlierNumber) {
        return new Profile();
    }
}
