package com.manning.siia.business;

/**
 * @author Marius Bogoevici
 */
public class StubFrequentFlyerService implements FrequentFlyerService {

    @Override
    public Profile lookupProfile(String frequentFlierNumber) {
        return new Profile();
    }
}
