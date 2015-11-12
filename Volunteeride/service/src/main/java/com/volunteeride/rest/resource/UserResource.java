package com.volunteeride.rest.resource;

import com.volunteeride.dao.UserDAO;
import com.volunteeride.model.VolunteerideUser;
import com.volunteeride.service.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by ayazlakdawala on 11/8/15.
 */
@Named
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserService userService;

    @Inject
    private UserDAO userDAO;

    @POST
    public Response registerUser(VolunteerideUser user) {

        VolunteerideUser newUser = userService.registerUser(user);

        return Response.status(Response.Status.CREATED).entity(newUser).build();
    }

    @GET
    public Response retrieveUsers(){
        List<VolunteerideUser> users = userDAO.findAll();
        return Response.ok(users).build();
    }

}
