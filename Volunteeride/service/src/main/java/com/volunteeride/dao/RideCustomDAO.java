package com.volunteeride.dao;

import com.volunteeride.model.Ride;
import com.volunteeride.rest.resource.beans.RideSearchQueryCriteriaBean;
import org.springframework.data.domain.Page;

/**
 * Created by ayazlakdawala on 10/13/15.
 */
public interface RideCustomDAO {

    Page<Ride> searchRides(RideSearchQueryCriteriaBean rideSearchCriteria);
}
