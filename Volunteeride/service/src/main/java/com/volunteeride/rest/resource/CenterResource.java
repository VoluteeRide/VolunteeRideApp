package com.volunteeride.rest.resource;

import com.volunteeride.dao.CenterDAO;
import com.volunteeride.exception.RecordNotFoundException;
import com.volunteeride.model.Center;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.CENTER_EXCP_ARG_KEY;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.exceptionArgumentBundle;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionResourceConstants.RECORD_NOT_FOUND_EXCEPTION_KEY;

/**
 * This file serves as rest endpoint for Center related operation
 *
 * Created by ayazlakdawala on 9/18/2015.
 */
@Named
@Path("/centers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CenterResource {

    @Inject
    private RideResource rideResource;

    @Inject
    private CenterDAO centerDAO;

    @GET
    public Response getAllCenters() {
        List<Center> centers = centerDAO.findAll();
        return Response.ok(centers).build();
    }

    @Path("/{center_id}")
    @GET
    public Response getCenterDetails(@PathParam("center_id") String centerId) {
        Center retrievedCenter = centerDAO.findById(centerId);

        if(null != retrievedCenter){
            return Response.ok(retrievedCenter).build();
        } else{
            throw new RecordNotFoundException(RECORD_NOT_FOUND_EXCEPTION_KEY,
                    new Object[]{exceptionArgumentBundle.getString(CENTER_EXCP_ARG_KEY), centerId});
        }
    }


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
