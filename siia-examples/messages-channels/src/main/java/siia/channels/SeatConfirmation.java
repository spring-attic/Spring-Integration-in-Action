/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package siia.channels;

/**
 * @author Marius Bogoevici
 */
public class SeatConfirmation {

    private ChargedBooking chargedBooking;

    private Seat seat;

    public SeatConfirmation(ChargedBooking chargedBooking, Seat seat) {
        this.chargedBooking = chargedBooking;
        this.seat = seat;
    }

    public ChargedBooking getChargedBooking() {
        return chargedBooking;
    }

    public Seat getSeat() {
        return seat;
    }
}
