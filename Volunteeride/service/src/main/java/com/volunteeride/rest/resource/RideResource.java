package com.volunteeride.rest.resource;

import com.volunteeride.dao.RideDAO;
import com.volunteeride.exception.RecordNotFoundException;
import com.volunteeride.model.Ride;
import com.volunteeride.rest.resource.beans.RideSearchQueryCriteriaBean;
import com.volunteeride.service.ride.RideService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.BeanParam;
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

import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.RIDE_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.exceptionArgumentBundle;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionResourceConstants.RECORD_NOT_FOUND_EXCEPTION_KEY;

/**
 * This class represents sub-resource for Center Resource.
 * This Class serves as rest endpoint for Ride related operation
 * <p>
 * Created by ayazlakdawala on 9/18/2015.
 */
@Named
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RideResource {

    @Inject
    private RideService rideService;

    @Inject
    private RideDAO rideDAO;

    @GET
    public Response searchRides(@BeanParam RideSearchQueryCriteriaBean rideSearchCriteria) {

        Page<Ride> searchedRides = rideDAO.searchRides(rideSearchCriteria);
        return Response.ok(searchedRides).build();
    }

    @POST
    public Response requestRide(@PathParam("center_id") String centerId, Ride ride, @Context UriInfo uriInfo) {

        //set the center id from the path param in the ride object.
        ride.setCenterId(centerId);

        Ride newRide = rideService.requestRide(ride);

        //Build Location header.
        URI uri = uriInfo.getAbsolutePathBuilder().path(newRide.getId()).build();

        return Response.created(uri).entity(newRide).build();
    }

    @Path("/{ride_id}")
    @GET
    public Response getRideDetails(@PathParam("center_id") String centerId, @PathParam("ride_id") String rideId) {
        Ride retrievedRide = rideDAO.findByCenterIdAndId(centerId, rideId);

        if(null != retrievedRide){
            return Response.ok(retrievedRide).build();
        } else{
            throw new RecordNotFoundException(RECORD_NOT_FOUND_EXCEPTION_KEY,
                    new Object[]{exceptionArgumentBundle.getString(RIDE_EXCP_ARG_KEY), rideId});
        }
    }

}
