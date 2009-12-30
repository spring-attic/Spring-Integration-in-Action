package com.manning.siia.domain;

import java.util.Collections;
import java.util.List;

/**
 * Command that is sent by the form when a user is starting to plan a trip. It contains subcommands for all the
 * components of the trip.
 *
 * @author iwein
 */
public class CreateTripCommand {

    private final List<Command> subCommands;

    public CreateTripCommand(List<Command> subCommands) {
        this.subCommands = Collections.unmodifiableList(subCommands);
    }

    public List<Command> getSubCommands() {
        return subCommands;
    }
}
