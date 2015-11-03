package com.volunteeride.service.impl;

import com.volunteeride.dao.CenterDAO;
import com.volunteeride.dao.RideDAO;
import com.volunteeride.dao.UserDAO;
import com.volunteeride.dto.UserTypeRideStateKey;
import com.volunteeride.dto.UserTypeRideStateOperationKey;
import com.volunteeride.exception.RecordNotFoundException;
import com.volunteeride.exception.ValidationException;
import com.volunteeride.model.Ride;
import com.volunteeride.model.RideOperationEnum;
import com.volunteeride.model.RideStatusEnum;
import com.volunteeride.model.UserRoleEnum;
import com.volunteeride.model.VolunteerideUser;
import com.volunteeride.service.RideService;
import com.volunteeride.service.UserService;
import com.volunteeride.util.exception.ValidationExceptionUtil;
import com.volunteeride.util.statetransition.RideStateTransition;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.CENTER_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.CENTER_ID_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.RIDE_DROPOFF_LOC_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.RIDE_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.RIDE_PICKUP_LOC_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.RIDE_PICKUP_TIME_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.RIDE_SEEKERS_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.exceptionArgumentBundle;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionResourceConstants.RECORD_NOT_FOUND_EXCEPTION_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionResourceConstants.RIDE_PICK_UP_TIME_VALIDATION_EXCEPTION_KEY;
import static com.volunteeride.model.RideStatusEnum.REQUESTED;
import static com.volunteeride.model.UserRoleEnum.RIDE_SEEKER;
import static com.volunteeride.model.UserRoleEnum.VOLUNTEER;
import static com.volunteeride.util.statetransition.RideStateTransition.userRideOperationsMap;

/**
 * Created by ayazlakdawala on 8/31/15.
 */
@Named
public class RideServiceImpl implements RideService {
    
    @Inject
    private RideDAO rideDAO;

    @Inject
    private CenterDAO centerDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private UserService userService;

    @Override
    public Ride requestRide(Ride ride) {

        this.validateRideForSaveOperation(ride);
        ride.setStatus(REQUESTED);
        return rideDAO.save(ride);
    }

    /**
     * Api to execute ride operation.
     *
     * @param rideId
     * @param rideOperation
     * @return
     */
    //TODO Ayaz resolve retrieving user in session
    //TODO Ayaz Write Test
    @Override
    public Ride executeRideOperation(String centerId, String rideId, RideOperationEnum rideOperation) {

        Ride retrievedRide = rideDAO.findByCenterIdAndId(centerId, rideId);

        if(null == retrievedRide){
            throw new RecordNotFoundException(RECORD_NOT_FOUND_EXCEPTION_KEY,
                    new Object[]{exceptionArgumentBundle.getString(RIDE_EXCP_ARG_KEY), rideId});
        }

        VolunteerideUser loggedInUser = userService.getLoggedInUserDetails();

        UserRoleEnum userRideRole = this.validateUserAccessToRideAndRetrieveUserRideRole(loggedInUser, retrievedRide);

        //if the ride is in REQUESTED state and the loggend in user trying to perform an operation on the ride is
        // a volunteer, set logged in user as a volunteer for the ride.
        if(userRideRole.name().equals(VOLUNTEER.name()) &&
                retrievedRide.getStatus().name().equals(REQUESTED.name())){
            retrievedRide.setVolunteerId(loggedInUser.getId());
        }

        List<RideOperationEnum> validRideOperationsForLoggedInUser = userRideOperationsMap
                .get(new UserTypeRideStateKey(userRideRole, retrievedRide.getStatus()));


        if(!validRideOperationsForLoggedInUser.contains(rideOperation)){
            //TODO AYAZ Throw Invalid operation exception
        }

        RideStatusEnum rideCurrentState = retrievedRide.getStatus();

        RideStatusEnum rideTransitionedState = RideStateTransition.transitionedRideStateMap
                .get(new UserTypeRideStateOperationKey(userRideRole, rideCurrentState, rideOperation));

        if(rideTransitionedState == null){
            //TODO AYAZ Throw Operation cannot be performed exception

        }

        retrievedRide.setStatus(rideTransitionedState);

        if(rideTransitionedState.name().equals(REQUESTED.name())){

            //reset the volunteer id if the transitioned state of the Ride is "REQUESTED"
            retrievedRide.setVolunteerId(null);
        }

        Ride updatedRide = rideDAO.save(retrievedRide);

        List<RideOperationEnum> nextRideOperations = userRideOperationsMap
                .get(new UserTypeRideStateKey(userRideRole, updatedRide.getStatus()));

        // ensure that you do not persist next ride user operations in the database as it should be determined by the
        // system when the ride details are rendered to the client.
        updatedRide.setNextRideUserOperations(nextRideOperations);

        return updatedRide;
    }

    /**
     * Api to retrieve Ride details
     *
     * @param centerId
     * @param rideId
     * @return
     */
    //TODO Ayaz Write Test
    @Override
    public Ride retrieveRideDetails(String centerId, String rideId) {

        Ride retrievedRide = rideDAO.findByCenterIdAndId(centerId, rideId);

        if(null == retrievedRide){
            throw new RecordNotFoundException(RECORD_NOT_FOUND_EXCEPTION_KEY,
                    new Object[]{exceptionArgumentBundle.getString(RIDE_EXCP_ARG_KEY), rideId});
        }

        //TODO Ayaz Un-comment this when user login functionality fully implemented
        VolunteerideUser loggedInUser = userService.getLoggedInUserDetails();

        UserRoleEnum userRideRole = this.validateUserAccessToRideAndRetrieveUserRideRole(loggedInUser, retrievedRide);

        //UserRoleEnum userRideRole = null;

        List<RideOperationEnum> nextRideOperations = userRideOperationsMap
                .get(new UserTypeRideStateKey(userRideRole, retrievedRide.getStatus()));

        retrievedRide.setNextRideUserOperations(nextRideOperations);

        return retrievedRide;
    }

    private void validateRideForSaveOperation(Ride ride) {
        //validate required data
        ValidationExceptionUtil.validateForEmptyOrNull(ride, new Object[]{RIDE_EXCP_ARG_KEY});

        //validate center
        ValidationExceptionUtil.validateForEmptyOrNull(ride.getCenterId(), new Object[]{CENTER_ID_EXCP_ARG_KEY});

        if(!centerDAO.exists(ride.getCenterId())){
            throw new RecordNotFoundException(RECORD_NOT_FOUND_EXCEPTION_KEY,
                    new Object[]{exceptionArgumentBundle.getString(CENTER_EXCP_ARG_KEY), ride.getCenterId()});
        }

        ValidationExceptionUtil.validateForEmptyOrNull(ride.getPickupLoc(), new Object[]{RIDE_PICKUP_LOC_EXCP_ARG_KEY});
        ValidationExceptionUtil.validateForEmptyOrNull(ride.getDropoffLoc(), new Object[]{RIDE_DROPOFF_LOC_EXCP_ARG_KEY});

        //validate pick up time
        ValidationExceptionUtil.validateForEmptyOrNull(ride.getPickupTime(), new Object[]{RIDE_PICKUP_TIME_EXCP_ARG_KEY});

        if(ride.getPickupTime().isBeforeNow() || (ride.getPickupTime().getDayOfYear() > DateTime.now().plusDays(1).getDayOfYear())){
            throw new ValidationException(RIDE_PICK_UP_TIME_VALIDATION_EXCEPTION_KEY);
        }

        //validate Ride Seekers
        ValidationExceptionUtil.validateForEmptyOrNull(ride.getRideSeekerIds(), new Object[]{RIDE_SEEKERS_EXCP_ARG_KEY});

        StringBuilder invalidRideSeekers = null;

        for(String rideSeekerId : ride.getRideSeekerIds()){

            if(!userDAO.exists(rideSeekerId)){

                if(null == invalidRideSeekers){
                    invalidRideSeekers = new StringBuilder();
                }
                invalidRideSeekers.append(rideSeekerId);
                invalidRideSeekers.append(" ");
            }
        }

        if(null != invalidRideSeekers){
            throw new RecordNotFoundException(RECORD_NOT_FOUND_EXCEPTION_KEY,
                    new Object[]{exceptionArgumentBundle.getString(RIDE_SEEKERS_EXCP_ARG_KEY),
                            invalidRideSeekers.toString().trim()});
        }
    }


    /**
     * This is a helper method which determines if the user has access to the ride and returns the role of the user
     * in context of specific ride.
     *
     * @param ride
     * @return
     */
    //TODO Ayaz resolve retrieving user in session
    //TODO Ayaz Write Tests
    private UserRoleEnum validateUserAccessToRideAndRetrieveUserRideRole(VolunteerideUser user, Ride ride){

        String userId = user.getId();

        List<UserRoleEnum> userRoles = user.getUserRoles();

        UserRoleEnum userRideRole = null;

        if(ride.getRideSeekerIds().contains(userId) && userRoles.contains(RIDE_SEEKER)){

            userRideRole = RIDE_SEEKER;

        } else if((ride.getVolunteerId() != null && ride.getVolunteerId().equals(userId) &&
                userRoles.contains(VOLUNTEER)) ||
                (ride.getVolunteerId() == null && userRoles.contains(VOLUNTEER))){

            userRideRole = VOLUNTEER;

        } else {
            //TODO AYAZ Throw Access Denied Forbidden 403 exception
        }

        return userRideRole;

    }


}
