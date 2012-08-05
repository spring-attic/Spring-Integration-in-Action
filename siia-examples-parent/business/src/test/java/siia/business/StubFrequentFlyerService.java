package siia.business;

import org.springframework.stereotype.Component;
import siia.business.FrequentFlyerService;
import siia.business.Profile;

/**
 * @author Marius Bogoevici
 */
@Component
public class StubFrequentFlyerService implements FrequentFlyerService {

    @Override
    public Profile lookupProfile(String frequentFlierNumber) {
        Profile profile = new Profile();
        profile.setEmailAddress("user@example.com");
        return profile;
    }
}
