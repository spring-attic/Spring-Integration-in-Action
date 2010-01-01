package com.manning.siia.domain.car;

import com.manning.siia.domain.Location;

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
