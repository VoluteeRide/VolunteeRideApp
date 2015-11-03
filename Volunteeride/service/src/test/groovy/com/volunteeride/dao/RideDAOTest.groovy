package com.volunteeride.dao

import com.volunteeride.common.BaseDAOTest
import com.volunteeride.model.*
import org.bson.types.ObjectId
import org.joda.time.DateTime
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.annotation.Rollback

import javax.inject.Inject

/**
 * Created by ayazlakdawala on 9/17/2015.
 */
class RideDAOTest extends BaseDAOTest {

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private RideDAO rideDAO

    @Inject
    private CenterDAO centerDAO

    @Inject
    private UserDAO userDAO

    def center
    def pickUpLoc
    def dropoffLoc
    def rideseeker
    def volunteer

    void setup() {

        pickUpLoc = new Location()
        pickUpLoc.city = "Fairfax"
        pickUpLoc.state = "Virginia"

        dropoffLoc = new Location()
        dropoffLoc.city = "Falls Church"
        dropoffLoc.state = "Virginia"

        //Insert Center
        center = new Center()

        def centerLoc = new Location()
        centerLoc.city = "Falls Church"
        centerLoc.state = "Virginia"
        center.location = centerLoc

        def centerPickUpLoc = new ArrayList<Location>()
        centerPickUpLoc << pickUpLoc
        center.pickUpLocations = centerPickUpLoc

        centerDAO.save(center)

        //Insert Ride Seeker
        rideseeker = new VolunteerideUser()
        rideseeker.firstName = "rideseeker First Name"
        rideseeker.lastName = "rideseeker last name"
        rideseeker.phone = "123-456-8792"
        rideseeker.username = "rideusr"
        rideseeker.password = passwordEncoder.encode("password");

        def rideSeekerUserRoles = new ArrayList<UserRoleEnum>()
        rideSeekerUserRoles << UserRoleEnum.RIDE_SEEKER
        rideseeker.userRoles = rideSeekerUserRoles

        userDAO.save(rideseeker)

        //Insert Volunteer
        volunteer = new VolunteerideUser()
        volunteer.firstName = "volunteer First Name"
        volunteer.lastName = "volunteer last name"
        volunteer.phone = "123-456-8792"
        volunteer.username = "volusr"
        volunteer.password = passwordEncoder.encode("password");

        def volunteerUserRoles = new ArrayList<UserRoleEnum>()
        volunteerUserRoles << UserRoleEnum.VOLUNTEER
        volunteer.userRoles = volunteerUserRoles

        userDAO.save(volunteer)
    }

    @Rollback(false)
    def "test save ride "() {

        setup: "Set up ride object to insert"

        def ride = new Ride()
        ride.pickupLoc = pickUpLoc
        ride.dropoffLoc = dropoffLoc
        ride.centerId = center.id
        ride.pickupTime = new DateTime()
        ride.status = RideStatusEnum.REQUESTED

        def rideSeekers = new ArrayList<ObjectId>()
        rideSeekers << rideseeker.id

        ride.rideSeekerIds = rideSeekers

        when : "execute save method"
        def savedRide = rideDAO.save(ride)

        then : "assert that ride object saved successfully"
        savedRide != null
        savedRide.id != null

    }
}
