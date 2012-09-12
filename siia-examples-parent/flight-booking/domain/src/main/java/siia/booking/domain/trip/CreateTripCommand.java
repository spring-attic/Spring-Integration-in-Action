/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
