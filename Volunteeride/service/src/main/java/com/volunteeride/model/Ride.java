package com.volunteeride.model;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Model class representing Ride Object.
 *
 * Created by ayazlakdawala on 8/31/15.
 */
public class Ride extends BaseModelObject {

    private VolunteerideUser volunteer;
    private List<VolunteerideUser> rideSeekers;
    private Location pickupLoc;
    private Location dropoffLoc;
    private DateTime pickupTime;
    private RideStatusEnum status;
    private Center center;
    private int totalNoOfRiders;

    public VolunteerideUser getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(VolunteerideUser volunteer) {
        this.volunteer = volunteer;
    }

    public List<VolunteerideUser> getRideSeekers() {
        return rideSeekers;
    }

    public void setRideSeekers(List<VolunteerideUser> rideSeekers) {
        this.rideSeekers = rideSeekers;
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

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public int getTotalNoOfRiders() {
        return totalNoOfRiders;
    }

    public void setTotalNoOfRiders(int totalNoOfRiders) {
        this.totalNoOfRiders = totalNoOfRiders;
    }
}
