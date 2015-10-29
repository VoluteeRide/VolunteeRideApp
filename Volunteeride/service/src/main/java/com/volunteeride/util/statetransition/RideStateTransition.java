package com.volunteeride.util.statetransition;

import com.volunteeride.dto.UserTypeRideStateKey;
import com.volunteeride.dto.UserTypeRideStateOperationKey;
import com.volunteeride.model.RideOperationEnum;
import com.volunteeride.model.RideStatusEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.volunteeride.model.RideOperationEnum.ACCEPT;
import static com.volunteeride.model.RideOperationEnum.ACKNOWLEDGE;
import static com.volunteeride.model.RideOperationEnum.CANCEL;
import static com.volunteeride.model.RideStatusEnum.ACCEPTED;
import static com.volunteeride.model.RideStatusEnum.ACKNOWLEDGED;
import static com.volunteeride.model.RideStatusEnum.CANCELLED;
import static com.volunteeride.model.RideStatusEnum.REQUESTED;
import static com.volunteeride.model.UserRoleEnum.RIDE_SEEKER;
import static com.volunteeride.model.UserRoleEnum.VOLUNTEER;

/**
 * Created by ayazlakdawala on 10/25/15.
 */
public class RideStateTransition {

    public  static Map<UserTypeRideStateKey, List<RideOperationEnum>> userRideOperationsMap =
            getUserRideOperationsMapBasedOnCurrentRideStatus();

    public static Map<UserTypeRideStateOperationKey, RideStatusEnum> transitionedRideStateMap =
            getTransitionedRideStateMap();

    private static Map<UserTypeRideStateKey, List<RideOperationEnum>> getUserRideOperationsMapBasedOnCurrentRideStatus() {

        userRideOperationsMap = new HashMap<>();

        userRideOperationsMap.put(new UserTypeRideStateKey(RIDE_SEEKER, REQUESTED),
                new ArrayList<>(Arrays.asList(CANCEL)));

        userRideOperationsMap.put(new UserTypeRideStateKey(VOLUNTEER, REQUESTED),
                new ArrayList<>(Arrays.asList(ACCEPT)));

        userRideOperationsMap.put(new UserTypeRideStateKey(RIDE_SEEKER, ACCEPTED),
                new ArrayList<>(Arrays.asList(ACKNOWLEDGE, CANCEL)));

        userRideOperationsMap.put(new UserTypeRideStateKey(VOLUNTEER, ACCEPTED),
                new ArrayList<>(Arrays.asList(CANCEL)));

        userRideOperationsMap.put(new UserTypeRideStateKey(RIDE_SEEKER, ACKNOWLEDGED),
                new ArrayList<>(Arrays.asList(CANCEL)));

        userRideOperationsMap.put(new UserTypeRideStateKey(VOLUNTEER, ACKNOWLEDGED),
                new ArrayList<>(Arrays.asList(CANCEL)));

        return userRideOperationsMap;
    }


    private static Map<UserTypeRideStateOperationKey, RideStatusEnum> getTransitionedRideStateMap() {

        transitionedRideStateMap = new HashMap<>();

        transitionedRideStateMap.put(new UserTypeRideStateOperationKey(RIDE_SEEKER, REQUESTED, CANCEL), CANCELLED);

        transitionedRideStateMap.put(new UserTypeRideStateOperationKey(VOLUNTEER, REQUESTED, ACCEPT), ACCEPTED);

        transitionedRideStateMap.put(new UserTypeRideStateOperationKey(RIDE_SEEKER, ACCEPTED, CANCEL), CANCELLED);

        transitionedRideStateMap.put(new UserTypeRideStateOperationKey(RIDE_SEEKER, ACCEPTED, ACKNOWLEDGE), ACKNOWLEDGED);

        transitionedRideStateMap.put(new UserTypeRideStateOperationKey(VOLUNTEER, ACCEPTED, CANCEL), REQUESTED);

        transitionedRideStateMap.put(new UserTypeRideStateOperationKey(RIDE_SEEKER, ACKNOWLEDGED, CANCEL), CANCELLED);

        transitionedRideStateMap.put(new UserTypeRideStateOperationKey(VOLUNTEER, ACKNOWLEDGED, CANCEL), REQUESTED);

        return transitionedRideStateMap;
    }

}
