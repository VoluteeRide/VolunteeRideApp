package com.volunteeride.service.ride;

import com.volunteeride.model.Ride;

/**
 * Created by ayazlakdawala on 8/31/15.
 */
public interface RideService {

    /**
     * Api to request ride.
     * @param ride
     * @return Ride
     */
    Ride requestRide(Ride ride);
}
