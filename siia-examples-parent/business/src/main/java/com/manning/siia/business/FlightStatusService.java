package com.manning.siia.business;

/**
 * @author Marius Bogoevici
 */
public interface FlightStatusService {
    FlightStatus updateStatus(FlightDelayEvent flightDelayEvent);
}

