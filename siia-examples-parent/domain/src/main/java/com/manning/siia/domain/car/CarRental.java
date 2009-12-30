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

    public CarRental(Location collectionLocation, Location dropOffLocation, Date collectionDate, Date dropOffDate) {
        this.collectionLocation = collectionLocation;
        this.dropOffLocation = dropOffLocation;
        this.collectionDate = collectionDate;
        this.dropOffDate = dropOffDate;
    }

    public Location getCollectionLocation() {
        return collectionLocation;
    }

    public void setCollectionLocation(Location collectionLocation) {
        this.collectionLocation = collectionLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Location dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public Date getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }
}
