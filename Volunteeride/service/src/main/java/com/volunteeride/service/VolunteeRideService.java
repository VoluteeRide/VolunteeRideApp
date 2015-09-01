package com.volunteeride.service;

import com.volunteeride.model.Ride;

/**
 * Created by ayazlakdawala on 8/31/15.
 */
public interface VolunteeRideService {

    /**
     * Api to request ride.
     * @param ride
     * @return Ride
     */
    Ride requestRide(Ride ride);
}
