package com.volunteeride.dto;

import com.volunteeride.model.RideStatusEnum;
import com.volunteeride.model.UserTypeEnum;

/**
 * This class represents a wrapper key for representing hash map,
 * returning the list of operations a user can perform based on
 * user type and current state of the Ride.
 *
 * Created by ayazlakdawala on 10/25/15.
 */
public class UserTypeRideStateKey implements Comparable<UserTypeRideStateKey> {

    private String key;

    public UserTypeRideStateKey(UserTypeEnum userType, RideStatusEnum currentRideStatus) {
        this.key = userType.name() + currentRideStatus.name();
    }

    @Override
    public int compareTo(UserTypeRideStateKey other) {
        return key.compareTo(other.key);
    }

    @Override
    public boolean equals(Object other)
    {
        return (other != null) && (getClass() == other.getClass()) &&
                key.equals(((UserTypeRideStateKey)other).key);
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
