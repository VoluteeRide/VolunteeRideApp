package com.volunteeride.service.impl.ride

import com.volunteeride.model.Center
import com.volunteeride.model.Ride
import com.volunteeride.service.impl.ride.RideServiceImpl
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by ayazlakdawala on 9/1/15.
 */
class RideServiceImplTest extends Specification  {

    def ride = new Ride();
    def volunteerideService = new RideServiceImpl();

    @Shared
     center = new Center();

    void setup() {

    }

    void cleanup() {

    }

    def "validate ride object for Request Ride api "() {

        expect:
        volunteerideService.requestRide(ride)== null;

    }
}
