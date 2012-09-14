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

package siia.booking.domain.flight;

import org.joda.time.*;

/**
 * Flight schedule
 *
 * @author Jonas Partner
 */
public class FlightSchedule {

    private final DateTime validFromInclusive;

    private final DateTime validUntilInclusive;

    private final Partial flightTimeOfDay;

    

    public FlightSchedule(DateTime validFromInclusive, DateTime validUntilExclusive, int flightHourOfDay, int flightMinuteOfHour) {
        this.validFromInclusive = validFromInclusive;
        this.validUntilInclusive = validUntilExclusive;
        flightTimeOfDay = new Partial().with(DateTimeFieldType.hourOfDay(), 12).with(DateTimeFieldType.minuteOfHour(), 30);

    }

    public DateTime getValidFromInclusive() {
        return validFromInclusive;
    }

    public DateTime getValidUntilInclusive() {
        return validUntilInclusive;
    }

    public Partial getFlightTimeOfDay() {
        return flightTimeOfDay;
    }
}



