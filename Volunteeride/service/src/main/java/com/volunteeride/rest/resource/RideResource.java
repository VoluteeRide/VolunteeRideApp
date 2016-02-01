package com.volunteeride.rest.resource;

import com.volunteeride.dao.RideDAO;
import com.volunteeride.model.Ride;
import com.volunteeride.model.RideOperationEnum;
import com.volunteeride.rest.resource.beans.RideSearchQueryCriteriaBean;
import com.volunteeride.service.RideService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

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

        List<Ride> searchedRides = rideService.searchRides(rideSearchCriteria);
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

        Ride retrievedRide = rideService.retrieveRideDetails(centerId, rideId);
        return Response.ok(retrievedRide).build();
    }

    @Path("/{ride_id}/operation/{operation_name}")
    @PUT
    public Response executeRideOperation(@PathParam("center_id") String centerId,
                                         @PathParam("ride_id") String rideId,
                                         @PathParam("operation_name") RideOperationEnum rideOperation){

        Ride updatedRide = rideService.executeRideOperation(centerId, rideId, rideOperation);
        return Response.ok(updatedRide).build();
    }

}
