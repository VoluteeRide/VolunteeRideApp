package com.volunteeride.dao;

import com.volunteeride.model.Ride;
import com.volunteeride.rest.resource.beans.RideSearchQueryCriteriaBean;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by ayazlakdawala on 10/13/15.
 */
@Named
public class RideDAOImpl implements RideCustomDAO {

    @Inject
    MongoTemplate mongoTemplate;

    @Override
    public List<Ride> searchRides(RideSearchQueryCriteriaBean rideSearchCriteria) {

        Query searchQuery = new Query();

        if(rideSearchCriteria.getCenterId() != null){
            searchQuery.addCriteria(Criteria.where("centerId").is(rideSearchCriteria.getCenterId()));
        }

        if(CollectionUtils.isNotEmpty(rideSearchCriteria.getRideSeekerIds())){
            searchQuery.addCriteria(Criteria.where("rideSeekerIds").in(rideSearchCriteria.getRideSeekerIds()));
        }

        if(rideSearchCriteria.getVolunteerId() != null){
            searchQuery.addCriteria(Criteria.where("volunteerId").is(rideSearchCriteria.getVolunteerId()));
        }

        if(CollectionUtils.isNotEmpty(rideSearchCriteria.getStatus())){
            searchQuery.addCriteria(Criteria.where("status").in(rideSearchCriteria.getStatus()));
        }

        return mongoTemplate.find(searchQuery, Ride.class);
    }
}
