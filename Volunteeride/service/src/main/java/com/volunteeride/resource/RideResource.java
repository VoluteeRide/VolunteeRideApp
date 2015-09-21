package com.volunteeride.resource;

import com.volunteeride.dao.RideDAO;
import com.volunteeride.model.Ride;
import com.volunteeride.service.ride.RideService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * This file serves as rest endpoint for Ride related operation
 *
 * Created by ayazlakdawala on 9/18/2015.
 */
@Named
@Path("/centers/{center_id}/rides")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RideResource {

    @Inject
    private RideService rideService;

    @Inject
    private RideDAO rideDAO;

    @GET
    public List<Ride> findAll(){
        return rideDAO.findAll();
    }

    @POST
    public Ride requestRide (Ride ride){
        return rideService.requestRide(ride);
    }

}
