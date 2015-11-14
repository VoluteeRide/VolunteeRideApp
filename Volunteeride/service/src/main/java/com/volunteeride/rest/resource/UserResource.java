package com.volunteeride.rest.resource;

import com.volunteeride.dao.UserDAO;
import com.volunteeride.exception.RecordNotFoundException;
import com.volunteeride.model.VolunteerideUser;
import com.volunteeride.service.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.CENTER_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.USER_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.exceptionArgumentBundle;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionResourceConstants.RECORD_NOT_FOUND_EXCEPTION_KEY;

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
    public Response registerUser(VolunteerideUser user, @Context UriInfo uriInfo) {

        VolunteerideUser newUser = userService.registerUser(user);

        //Build Location header.
        URI uri = uriInfo.getAbsolutePathBuilder().path(newUser.getId()).build();

        return Response.created(uri).entity(newUser).build();
    }

    @GET
    //TODO Ayaz Delete this api if not required
    public Response retrieveUsers(){
        List<VolunteerideUser> users = userDAO.findAll();
        return Response.ok(users).build();
    }

    @Path("/{user_id}")
    @GET
    public Response getUserDetails(@PathParam("user_id") String userId) {

        VolunteerideUser retrievedUser = userDAO.findOne(userId);

        if(null != retrievedUser){
            return Response.ok(retrievedUser).build();
        } else{
            throw new RecordNotFoundException(RECORD_NOT_FOUND_EXCEPTION_KEY,
                    new Object[]{exceptionArgumentBundle.getString(USER_EXCP_ARG_KEY), userId});
        }
    }

}
