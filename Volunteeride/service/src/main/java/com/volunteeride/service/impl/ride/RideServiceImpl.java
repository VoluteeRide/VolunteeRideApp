package com.volunteeride.service.impl.ride;

import com.volunteeride.dao.RideDAO;
import com.volunteeride.model.Ride;
import com.volunteeride.service.ride.RideService;
import com.volunteeride.util.exception.ValidationExceptionUtil;

/**
 * Created by ayazlakdawala on 8/31/15.
 */
public class RideServiceImpl implements RideService {

    private RideDAO rideDAO;

    @Override
    public Ride requestRide(Ride ride) {
        ValidationExceptionUtil.validateForNull(ride, "Ride");
        ValidationExceptionUtil.validateForNull(ride.getCenter(), "Center");
        ValidationExceptionUtil.validateForNull(ride.getPickupLoc(), "Ride Pick up Location");
        ValidationExceptionUtil.validateForNull(ride.getDropoffLoc(), "Ride Drop off Location");
        ValidationExceptionUtil.validateForNull(ride.getPickupTime(), "Ride Pick Up Time");


        return ride;
    }
}
