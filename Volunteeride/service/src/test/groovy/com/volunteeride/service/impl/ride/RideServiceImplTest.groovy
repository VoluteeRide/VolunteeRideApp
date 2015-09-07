package com.volunteeride.service.impl.ride

import com.volunteeride.common.BaseUnitTest
import com.volunteeride.exception.ValidationException
import com.volunteeride.model.Center
import com.volunteeride.model.Location
import com.volunteeride.model.Ride
import org.joda.time.DateTime

/**
 * Created by ayazlakdawala on 9/1/15.
 */
class RideServiceImplTest extends BaseUnitTest  {

    def ride;
    def rideService = new RideServiceImpl()

    void setup() {
        ride = new Ride();
        ride.center = new Center()
        ride.pickupLoc = new Location()
        ride.dropoffLoc = new Location()
        ride.pickupTime = new DateTime()
    }

    void cleanup() {

    }

    def "validate ride object for Request Ride api "() {

        setup : "prepare ride object for test"
        def ride = null;

        when: "function under test is executed"
        rideService.requestRide(ride);

        then:
        def excp = thrown(ValidationException)
        this.testForNull(excp, "Ride")
    }

    def "validate Center object for Request Ride api "(){

        setup: "set up ride object for test"
        ride.center = null;

        when: "function under test is executed"
        rideService.requestRide(ride);

        then:
        def excp = thrown(ValidationException)
        this.testForNull(excp, "Center")
    }

    def "validate pick up location object for Request Ride api "(){

        setup: "set up ride object for test"
        ride.pickupLoc = null;

        when: "function under test is executed"
        rideService.requestRide(ride);

        then:
        def excp = thrown(ValidationException)
        this.testForNull(excp, "Ride Pick up Location")
    }

    def "validate drop off location object for Request Ride api "(){

        setup: "set up ride object for test"
        ride.dropoffLoc = null;

        when: "function under test is executed"
        rideService.requestRide(ride);

        then:
        def excp = thrown(ValidationException)
        this.testForNull(excp, "Ride Drop off Location")
    }

    def "validate pick up time object for Request Ride api "(){

        setup: "set up ride object for test"
        ride.pickupTime = null;

        when: "function under test is executed"
        rideService.requestRide(ride);

        then:
        def excp = thrown(ValidationException)
        this.testForNull(excp, "Ride Pick Up Time")
    }


}
