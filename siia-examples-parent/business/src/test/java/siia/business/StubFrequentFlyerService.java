package siia.business;

/**
 * @author Marius Bogoevici
 */
public class StubFrequentFlyerService implements FrequentFlyerService {

    @Override
    public Profile lookupProfile(String frequentFlierNumber) {
        Profile profile = new Profile();
        profile.setEmailAddress("user@example.com");
        return profile;
    }
}
