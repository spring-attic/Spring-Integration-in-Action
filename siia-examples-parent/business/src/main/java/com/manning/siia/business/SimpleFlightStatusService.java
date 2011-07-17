package com.manning.siia.business;

import org.springframework.integration.annotation.Publisher;

/**
 * @author Marius Bogoevici
 */
public class SimpleFlightStatusService implements FlightStatusService {
    @Override
    @Publisher(channel = "statisticsChannel")
    public FlightStatus updateStatus(FlightDelayEvent flightDelayEvent) {
        return new FlightStatus();
    }
}
