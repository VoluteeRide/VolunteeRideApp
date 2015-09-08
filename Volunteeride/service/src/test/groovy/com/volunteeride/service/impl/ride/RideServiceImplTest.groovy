package com.volunteeride.service.impl.ride

import com.volunteeride.common.BaseUnitTest
import com.volunteeride.dao.RideDAO
import com.volunteeride.exception.ValidationException
import com.volunteeride.model.Center
import com.volunteeride.model.Location
import com.volunteeride.model.Ride
import com.volunteeride.model.VolunteerideUser
import org.joda.time.DateTime

/**
 * Created by ayazlakdawala on 9/1/15.
 */
class RideServiceImplTest extends BaseUnitTest  {

    def ride
    def rideService = new RideServiceImpl()

    // create a mocked ride object
    def mockedRideDAO = Mock(RideDAO)

    void setup() {

        //inject mock object in the service
        rideService.rideDAO = mockedRideDAO

        ride = new Ride();
        ride.center = new Center()
        ride.pickupLoc = new Location()
        ride.dropoffLoc = new Location()
        ride.pickupTime = new DateTime()
        def rideseekers = new ArrayList<VolunteerideUser>()
        rideseekers << new VolunteerideUser()
        ride.rideSeekers = rideseekers
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
        this.testForEmptyOrNull(excp, "Ride")
    }

    def "validate Center object for Request Ride api "(){

        setup: "set up ride object for test"
        ride.center = null;

        when: "function under test is executed"
        rideService.requestRide(ride);

        then:
        def excp = thrown(ValidationException)
        this.testForEmptyOrNull(excp, "Center")
    }

    def "validate pick up location object for Request Ride api "(){

        setup: "set up ride object for test"
        ride.pickupLoc = null;

        when: "function under test is executed"
        rideService.requestRide(ride);

        then:
        def excp = thrown(ValidationException)
        this.testForEmptyOrNull(excp, "Ride Pick up Location")
    }

    def "validate drop off location object for Request Ride api "(){

        setup: "set up ride object for test"
        ride.dropoffLoc = null;

        when: "function under test is executed"
        rideService.requestRide(ride);

        then:
        def excp = thrown(ValidationException)
        this.testForEmptyOrNull(excp, "Ride Drop off Location")
    }

    def "validate pick up time object for Request Ride api "(){

        setup: "set up ride object for test"
        ride.pickupTime = null;

        when: "function under test is executed"
        rideService.requestRide(ride);

        then:
        def excp = thrown(ValidationException)
        this.testForEmptyOrNull(excp, "Ride Pick Up Time")
    }

    def "validate ride seekers for null for Request Ride api "(){

        setup: "set up ride object for test"
        ride.rideSeekers = null;

        when: "function under test is executed"
        rideService.requestRide(ride);

        then:
        def excp = thrown(ValidationException)
        this.testForEmptyOrNull(excp, "Ride Seekers")
    }

    def "validate ride seekers for empty list for Request Ride api "(){

        setup: "set up ride object for test"
        ride.rideSeekers = new ArrayList<VolunteerideUser>();

        when: "function under test is executed"
        rideService.requestRide(ride);

        then:
        def excp = thrown(ValidationException)
        this.testForEmptyOrNull(excp, "Ride Seekers")
    }

    def "test request ride api"(){

        setup : "set up expected ride object"
        ride.id = 1l

        when: "function under test is executed"
        def  actualRide = rideService.requestRide(ride);

        then:
        1 * mockedRideDAO.saveRide(_ as Ride) >> ride
        actualRide.id == ride.id
        actualRide.status == ride.status
    }


}
