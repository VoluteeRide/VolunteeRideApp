package com.volunteeride.service.impl;

import com.volunteeride.dao.UserDAO;
import com.volunteeride.exception.RecordNotFoundException;
import com.volunteeride.model.VolunteerideUser;
import com.volunteeride.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.inject.Inject;
import javax.inject.Named;

import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.CENTER_EXCP_ARG_KEY;
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
                    new Object[]{exceptionArgumentBundle.getString(CENTER_EXCP_ARG_KEY), principal.getUsername()});
        }

        return loggedInUser;
    }
}
