package com.volunteeride.rest.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

/**
 * This file serves as rest endpoint for Center related operation
 *
 * Created by ayazlakdawala on 9/18/2015.
 */
@Named
@Path("/centers")
public class CenterResource {

    @Inject
    private RideResource rideResource;


    /**
     * This method configures the sub-resource (RideResource) for the center resource.
     *
     * @return sub-resource RideResource
     */
    @Path("/{center_id}/rides")
    public RideResource getRideResource(){
        return rideResource;
    }


}
