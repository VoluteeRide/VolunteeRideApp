package com.volunteeride.rest.resource.beans;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * This Class defines all the search criteria query params for searching Rides
 *
 * Created by ayazlakdawala on 10/10/15.
 */
public class RideSearchQueryCriteriaBean extends PaginationAndSortingCriteriaBean {

    private @PathParam("center_id") String centerId;

    private @QueryParam("rideseeker_id") List<String> rideSeekerIds;

    private @QueryParam("status") String status;

    private @QueryParam("volunteer_id") String volunteerId;

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getRideSeekerIds() {
        return rideSeekerIds;
    }

    public void setRideSeekerIds(List<String> rideSeekerIds) {
        this.rideSeekerIds = rideSeekerIds;
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }

}
