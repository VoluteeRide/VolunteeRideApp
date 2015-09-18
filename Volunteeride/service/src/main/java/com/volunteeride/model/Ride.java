package com.volunteeride.model;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Model class representing Ride Object.
 *
 * Created by ayazlakdawala on 8/31/15.
 */
@Document
public class Ride extends BaseModelObject {

    private ObjectId volunteerId;
    private List<ObjectId> rideSeekerIds;
    private Location pickupLoc;
    private Location dropoffLoc;
    private DateTime pickupTime;
    private RideStatusEnum status;
    private ObjectId centerId;
    private int totalNoOfRiders;

    public ObjectId getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(ObjectId volunteerId) {
        this.volunteerId = volunteerId;
    }

    public List<ObjectId> getRideSeekerIds() {
        return rideSeekerIds;
    }

    public void setRideSeekerIds(List<ObjectId> rideSeekerIds) {
        this.rideSeekerIds = rideSeekerIds;
    }

    public Location getPickupLoc() {
        return pickupLoc;
    }

    public void setPickupLoc(Location pickupLoc) {
        this.pickupLoc = pickupLoc;
    }

    public Location getDropoffLoc() {
        return dropoffLoc;
    }

    public void setDropoffLoc(Location dropoffLoc) {
        this.dropoffLoc = dropoffLoc;
    }

    public DateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(DateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public RideStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RideStatusEnum status) {
        this.status = status;
    }

    public ObjectId getCenterId() {
        return centerId;
    }

    public void setCenterId(ObjectId centerId) {
        this.centerId = centerId;
    }

    public int getTotalNoOfRiders() {
        return totalNoOfRiders;
    }

    public void setTotalNoOfRiders(int totalNoOfRiders) {
        this.totalNoOfRiders = totalNoOfRiders;
    }
}
