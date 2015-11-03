package com.volunteeride.service;

import com.volunteeride.model.Ride;
import com.volunteeride.model.RideOperationEnum;

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

    /**
     *
     * Api to execute ride operation.
     * @param rideId
     * @param rideOperation
     * @return
     */
    Ride executeRideOperation(String centerId, String rideId, RideOperationEnum rideOperation);


    /**
     * Api to retrieve Ride details
     * @param centerId
     * @param rideId
     * @return
     */
    Ride retrieveRideDetails(String centerId, String rideId);
}
