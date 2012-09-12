package siia.booking.domain.trip;

import siia.booking.domain.Command;

import java.util.Collections;
import java.util.List;

/**
 * Command that is sent by the form when a user is starting to plan a trip. It contains subcommands for all the
 * components of the trip (e.g. its legs)
 *
 * @author Iwein Fuld
 */
public class CreateTripCommand {

    private final List<Command> subCommands;

    public CreateTripCommand(List<Command> subCommands) {
        this.subCommands = Collections.unmodifiableList(subCommands);
    }

    /**
     * @return the commands for the components of the related trip
     */
    public List<Command> getSubCommands() {
        return subCommands;
    }
}
