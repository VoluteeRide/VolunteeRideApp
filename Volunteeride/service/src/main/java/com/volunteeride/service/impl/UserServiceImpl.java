package com.volunteeride.service.impl;

import com.volunteeride.dao.UserDAO;
import com.volunteeride.exception.RecordNotFoundException;
import com.volunteeride.model.UserRoleEnum;
import com.volunteeride.model.Vehicle;
import com.volunteeride.model.VolunteerideUser;
import com.volunteeride.service.UserService;
import com.volunteeride.util.exception.ValidationExceptionUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.inject.Inject;
import javax.inject.Named;

import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.CENTER_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.USER_EMAIL_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.USER_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.USER_FIRST_NAME_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.USER_LAST_NAME_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.USER_NAME_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.USER_PASSWORD_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.USER_PHONE_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.USER_ROLES_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.USER_VEHICLES_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.USER_VEHICLE_MAKE_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.USER_VEHICLE_MODEL_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.USER_VEHICLE_RIDER_CAPACITY_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.USER_VEHICLE_TYPE_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.exceptionArgumentBundle;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionResourceConstants.RECORD_NOT_FOUND_EXCEPTION_KEY;

/**
 * Created by ayazlakdawala on 11/2/15.
 */
@Named
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;

    @Override
    //TODO Ayaz Write tests and cover base scenarios
    public VolunteerideUser getLoggedInUserDetails() {

        VolunteerideUser loggedInUser = null;

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal != null){
            loggedInUser = userDAO.findByUsername(principal.getUsername());
        } else{
            throw new RecordNotFoundException(RECORD_NOT_FOUND_EXCEPTION_KEY,
                    new Object[]{exceptionArgumentBundle.getString(USER_EXCP_ARG_KEY), principal.getUsername()});
        }

        return loggedInUser;
    }

    @Override
    public VolunteerideUser registerUser(VolunteerideUser user) {

        this.validateUserForResgistration(user);

        return userDAO.save(user);
    }

    //TODO Ayaz Write Tests
    private void validateUserForResgistration(VolunteerideUser user) {

        ValidationExceptionUtil.validateForEmptyOrNull(user, new Object[]{USER_EXCP_ARG_KEY});
        ValidationExceptionUtil.validateForEmptyOrNull(user.getUsername(), new Object[]{USER_NAME_EXCP_ARG_KEY});
        ValidationExceptionUtil.validateForEmptyOrNull(user.getPassword(), new Object[]{USER_PASSWORD_EXCP_ARG_KEY});
        ValidationExceptionUtil.validateForEmptyOrNull(user.getFirstName(), new Object[]{USER_FIRST_NAME_EXCP_ARG_KEY});
        ValidationExceptionUtil.validateForEmptyOrNull(user.getLastName(), new Object[]{USER_LAST_NAME_EXCP_ARG_KEY});
        ValidationExceptionUtil.validateForEmptyOrNull(user.getPhone(), new Object[]{USER_PHONE_EXCP_ARG_KEY});
        ValidationExceptionUtil.validateForEmptyOrNull(user.getUserRoles(), new Object[]{USER_ROLES_EXCP_ARG_KEY});
        ValidationExceptionUtil.validateForEmptyOrNull(user.getEmail(), new Object[]{USER_EMAIL_EXCP_ARG_KEY});
        ValidationExceptionUtil.validateForEmptyOrNull(user.getCenterId(), new Object[]{CENTER_EXCP_ARG_KEY});

        if(user.getUserRoles().contains(UserRoleEnum.VOLUNTEER)){
            ValidationExceptionUtil.validateForEmptyOrNull(user.getOwnedVehicles(),
                    new Object[]{USER_VEHICLES_EXCP_ARG_KEY});

            user.getOwnedVehicles().forEach(this::validateVolunteerVehicle);
        }
    }

    private void validateVolunteerVehicle(Vehicle vehicle){
        ValidationExceptionUtil.validateForEmptyOrNull(vehicle.getMake(),
                new Object[]{USER_VEHICLE_MAKE_EXCP_ARG_KEY});

        ValidationExceptionUtil.validateForEmptyOrNull(vehicle.getModel(),
                new Object[]{USER_VEHICLE_MODEL_EXCP_ARG_KEY});

        ValidationExceptionUtil.validateForEmptyOrNull(vehicle.getTotalRiderCapacity(),
                new Object[]{USER_VEHICLE_RIDER_CAPACITY_EXCP_ARG_KEY});

        ValidationExceptionUtil.validateForEmptyOrNull(vehicle.getType(),
                new Object[]{USER_VEHICLE_TYPE_EXCP_ARG_KEY});
    }
}
