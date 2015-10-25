package com.volunteeride.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String volunteerId;
    private List<String> rideSeekerIds;
    private Location pickupLoc;
    private Location dropoffLoc;
    private DateTime pickupTime;
    private RideStatusEnum status;

    @JsonIgnore
    private String centerId;
    private int totalNoOfRiders;

    @JsonIgnore
    private List<RideOperationEnum> nextRideUserOperations;


    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }

    public List<String> getRideSeekerIds() {
        return rideSeekerIds;
    }

    public void setRideSeekerIds(List<String> rideSeekerIds) {
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

    @JsonProperty
    public String getCenterId() {
        return centerId;
    }

    @JsonIgnore
    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public int getTotalNoOfRiders() {
        return totalNoOfRiders;
    }

    public void setTotalNoOfRiders(int totalNoOfRiders) {
        this.totalNoOfRiders = totalNoOfRiders;
    }

    @JsonProperty
    public List<RideOperationEnum> getNextRideUserOperations() {
        return nextRideUserOperations;
    }

    @JsonIgnore
    public void setNextRideUserOperations(List<RideOperationEnum> nextRideUserOperations) {
        this.nextRideUserOperations = nextRideUserOperations;
    }
}
