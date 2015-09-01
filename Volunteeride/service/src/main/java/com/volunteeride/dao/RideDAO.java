package com.volunteeride.dao;

import com.volunteeride.model.Ride;

/**
 * This class represents Data Access Object level operations for Ride Object.
 *
 * Created by ayazlakdawala on 9/1/15.
 */
public interface RideDAO {

    /**
     * This Method persists the ride object in the database.
     *
     * @param ride
     * @return Ride
     */
    Ride saveRide(Ride ride);
}
