package com.volunteeride.model;

import java.util.List;

/**
 * Model class representing Ride Object.
 *
 * Created by ayazlakdawala on 8/31/15.
 */
public class Ride {

    private int rideId;
    private int volunteerId;
    private List<Integer> rideSeekerIds;
    private Location sourceLoc;
    private Location destinationLoc;
    private String pickupTime;
    private RideStatusEnum status;
    private Center center;
    private int totalNoOfRiders;

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public int getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public List<Integer> getRideSeekerIds() {
        return rideSeekerIds;
    }

    public void setRideSeekerIds(List<Integer> rideSeekerIds) {
        this.rideSeekerIds = rideSeekerIds;
    }

    public Location getSourceLoc() {
        return sourceLoc;
    }

    public void setSourceLoc(Location sourceLoc) {
        this.sourceLoc = sourceLoc;
    }

    public Location getDestinationLoc() {
        return destinationLoc;
    }

    public void setDestinationLoc(Location destinationLoc) {
        this.destinationLoc = destinationLoc;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
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
