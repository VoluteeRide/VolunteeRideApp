package com.volunteeride.service.impl.ride;

import com.volunteeride.common.constants.VolunteerideApplicationConstants;
import com.volunteeride.dao.CenterDAO;
import com.volunteeride.dao.RideDAO;
import com.volunteeride.model.Ride;
import com.volunteeride.model.RideStatusEnum;
import com.volunteeride.service.ride.RideService;
import com.volunteeride.util.exception.ValidationExceptionUtil;

import javax.inject.Inject;
import javax.inject.Named;

import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.*;

/**
 * Created by ayazlakdawala on 8/31/15.
 */
@Named
public class RideServiceImpl implements RideService {

    @Inject
    private RideDAO rideDAO;

    @Inject
    private CenterDAO centerDAO;

    @Override
    public Ride requestRide(Ride ride) {

        //validate required data
        ValidationExceptionUtil.validateForEmptyOrNull(ride,
                new Object[]{RIDE_EXCP_ARG});

        //validate center
        ValidationExceptionUtil.validateForEmptyOrNull(ride.getCenterId(),
                new Object[]{CENTER_ID_EXCP_ARG});

        /*if(!centerDAO.exists(ride.getCenterId())){

        }*/

        ValidationExceptionUtil.validateForEmptyOrNull(ride.getPickupLoc(),
                new Object[]{RIDE_PICKUP_LOC_EXCP_ARG});
        ValidationExceptionUtil.validateForEmptyOrNull(ride.getDropoffLoc(),
                new Object[]{RIDE_DROPOFF_LOC_EXCP_ARG});
        ValidationExceptionUtil.validateForEmptyOrNull(ride.getPickupTime(),
                new Object[]{RIDE_PICKUP_TIME_EXCP_ARG});
        ValidationExceptionUtil.validateForEmptyOrNull(ride.getRideSeekerIds(),
                new Object[]{RIDE_SEEKERS_EXCP_ARG});

        ride.setStatus(RideStatusEnum.REQUESTED);

        return rideDAO.save(ride);
    }
}
