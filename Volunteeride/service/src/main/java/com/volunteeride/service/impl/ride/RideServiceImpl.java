package com.volunteeride.service.impl.ride;

import com.volunteeride.common.constants.VolunteerideApplicationConstants;
import com.volunteeride.dao.CenterDAO;
import com.volunteeride.dao.RideDAO;
import com.volunteeride.dao.UserDAO;
import com.volunteeride.exception.RecordNotFoundException;
import com.volunteeride.exception.ValidationException;
import com.volunteeride.model.Ride;
import com.volunteeride.model.RideStatusEnum;
import com.volunteeride.service.ride.RideService;
import com.volunteeride.util.exception.ValidationExceptionUtil;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;

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

    @Override
    public Ride requestRide(Ride ride) {

        this.validateRideForSaveOperation(ride);
        ride.setStatus(RideStatusEnum.REQUESTED);
        return rideDAO.save(ride);
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
}
