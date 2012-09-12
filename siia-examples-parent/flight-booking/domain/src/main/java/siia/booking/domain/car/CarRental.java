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

package siia.booking.domain.car;

import siia.booking.domain.Location;

import java.util.Date;

/**
 * @author Jonas Partner
 */
public class CarRental {

    private Location collectionLocation;

    private Location dropOffLocation;

    private Date collectionDate;

    private Date dropOffDate;

    private String bookingConfirmation;

    public CarRental(Location collectionLocation, Location dropOffLocation, Date collectionDate, Date dropOffDate, String bookingConfirmation) {
        this.collectionLocation = collectionLocation;
        this.dropOffLocation = dropOffLocation;
        this.collectionDate = collectionDate;
        this.dropOffDate = dropOffDate;
        this.bookingConfirmation = bookingConfirmation;
    }

    public Location getCollectionLocation() {
        return collectionLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public Date getDropOffDate() {
        return dropOffDate;
    }

    public String getBookingConfirmation() {
        return bookingConfirmation;
    }

    public void setCollectionLocation(Location collectionLocation) {
        this.collectionLocation = collectionLocation;
    }

    public void setDropOffLocation(Location dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public void setBookingConfirmation(String bookingConfirmation) {
        this.bookingConfirmation = bookingConfirmation;
    }
}
