package com.volunteeride.dto;

import com.volunteeride.model.RideOperationEnum;
import com.volunteeride.model.RideStatusEnum;
import com.volunteeride.model.UserTypeEnum;

/**
 * This class represents a wrapper key for representing hash map,
 * returning the transitioned state of the ride based on
 * user type, current state, and operation being performed on the Ride.
 *
 * Created by ayazlakdawala on 10/25/15.
 */
public class UserTypeRideStateOperationKey implements Comparable<UserTypeRideStateOperationKey> {

    private String key;

    public UserTypeRideStateOperationKey(UserTypeEnum userType, RideStatusEnum currentRideStatus, RideOperationEnum rideOperation) {
        this.key = userType.name() + currentRideStatus.name() + rideOperation.name();
    }

    @Override
    public int compareTo(UserTypeRideStateOperationKey other) {
        return key.compareTo(other.key);
    }

    @Override
    public boolean equals(Object other)
    {
        return (other != null) && (getClass() == other.getClass()) &&
                key.equals(((UserTypeRideStateOperationKey)other).key);
    }

    @Override
    public int hashCode()
    {
        return key.hashCode();
    }

    @Override
    public String toString()
    {
        return key;
    }
}
