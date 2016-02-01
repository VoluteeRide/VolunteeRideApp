package com.volunteeride.dao;

import com.volunteeride.model.Ride;
import com.volunteeride.rest.resource.beans.RideSearchQueryCriteriaBean;

import java.util.List;

/**
 * Created by ayazlakdawala on 10/13/15.
 */
public interface RideCustomDAO {

    List<Ride> searchRides(RideSearchQueryCriteriaBean rideSearchCriteria);
}
