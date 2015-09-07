package com.volunteeride.service.impl.ride

import com.volunteeride.common.BaseUnitTest
import com.volunteeride.exception.BaseVolunteerideRuntimeException
import com.volunteeride.exception.ValidationException
import com.volunteeride.model.Center
import com.volunteeride.model.Ride
import spock.lang.Shared

/**
 * Created by ayazlakdawala on 9/1/15.
 */
class RideServiceImplTest extends BaseUnitTest  {

    def ride = new Ride()
    def rideService = new RideServiceImpl()

    @Shared
     center = new Center()

    void setup() {

    }

    void cleanup() {

    }

    def "validate ride object for Request Ride api "() {

        setup : "prepare ride object for test"
        def ride = null;

        and : "prepare exception expectation"
        def expectedMsg =  "Required Data is missing : Ride"
        //TODO Ayaz fill in these values once implemented
        def expectedErrCode = "VR1-01"
        def expectedCustomCause = ""
        def expectedResolution = "Please provide the required data."

        when:
        rideService.requestRide(ride);

        then:
        def excp = thrown(ValidationException)
        this.assertExpectedException(excp, expectedMsg, expectedCustomCause, expectedResolution, expectedErrCode)

    }
}
