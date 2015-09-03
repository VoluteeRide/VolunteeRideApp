package com.volunteeride.service.impl

import com.volunteeride.model.Center
import com.volunteeride.model.Ride
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by ayazlakdawala on 9/1/15.
 */
class VolunteeRideServiceImplTest extends Specification  {

    def ride = new Ride();
    def volunteerideService = new VolunteeRideServiceImpl();

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
