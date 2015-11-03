package com.volunteeride.service.impl;

import com.volunteeride.dao.UserDAO;
import com.volunteeride.model.VolunteerideUser;
import com.volunteeride.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.inject.Inject;
import javax.inject.Named;

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
            //TODO Ayaz Throw Exception
        }

        return loggedInUser;
    }
}
