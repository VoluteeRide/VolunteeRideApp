package com.volunteeride.common.constants;

import java.util.ResourceBundle;

/**
 * Created by ayazlakdawala on 9/26/15.
 */
public class VolunteerideApplicationConstants {

    public static class ExceptionKeyConstants {

        public static final String CAUSE_KEY_SUFFIX = ".cause";

        public static final String RESOLUTION_KEY_SUFFIX = ".resolution";

        public static final String ERROR_CODE_KEY_SUFFIX = ".errorcode";
    }

    public static class ExceptionResourceConstants {

        public static ResourceBundle exceptionResourceBundle = ResourceBundle.getBundle("exception-resource");

        public static final String REQUIRED_DATA_MISSING_EXCEPTION_KEY = "required.data.missing.exception";

        public static final String RECORD_NOT_FOUND_EXCEPTION_KEY = "record.not.found.exception";

        public static final String RIDE_PICK_UP_TIME_VALIDATION_EXCEPTION_KEY = "ride.pick.up.time.validation.exception";

        public static final String RIDE_ACCESS_DENIED_EXCEPTION = "ride.access.denied.exception";

        public static final String API_ACCESS_DENIED_EXCEPTION = "api.access.denied.exception";

        public static final String INVALID_USER_RIDE_OPERATION_EXCEPTION_KEY = "invalid.user.ride.operation.exception";

        public static final String INVALID_RIDE_STATE_TRANSITION_EXCEPTION_KEY = "invalid.ride.state.transition.exception";

    }

    public static class ExceptionArgumentConstants {

        public static ResourceBundle exceptionArgumentBundle = ResourceBundle.getBundle("exception-arguments");

        //Ride Specific Exception Arguments
        public static final String RIDE_EXCP_ARG_KEY = "ride";
        public static final String RIDE_PICKUP_LOC_EXCP_ARG_KEY = "ride.pickup.loc";
        public static final String RIDE_DROPOFF_LOC_EXCP_ARG_KEY = "ride.dropoff.loc";
        public static final String RIDE_PICKUP_TIME_EXCP_ARG_KEY = "ride.pickup.time";
        public static final String RIDE_SEEKERS_EXCP_ARG_KEY = "ride.seekers";

        //Center Specific
        public static final String CENTER_ID_EXCP_ARG_KEY = "center.id";
        public static final String CENTER_EXCP_ARG_KEY = "center";

        //User Specific
        public static final String USER_EXCP_ARG_KEY = "user";
        public static final String USER_NAME_EXCP_ARG_KEY = "user.username";
        public static final String USER_PASSWORD_EXCP_ARG_KEY = "user.password";
        public static final String USER_FIRST_NAME_EXCP_ARG_KEY = "user.first.name";
        public static final String USER_LAST_NAME_EXCP_ARG_KEY = "user.last.name";
        public static final String USER_PHONE_EXCP_ARG_KEY = "user.phone";
        public static final String USER_ROLES_EXCP_ARG_KEY = "user.roles";
        public static final String USER_VEHICLES_EXCP_ARG_KEY = "user.vehicles";
        public static final String USER_VEHICLE_MAKE_EXCP_ARG_KEY = "user.vehicle.make";
        public static final String USER_VEHICLE_MODEL_EXCP_ARG_KEY = "user.vehicle.model";
        public static final String USER_VEHICLE_RIDER_CAPACITY_EXCP_ARG_KEY = "user.vehicle.rider.capacity";
        public static final String USER_VEHICLE_TYPE_EXCP_ARG_KEY = "user.vehicle.type";



    }






}
