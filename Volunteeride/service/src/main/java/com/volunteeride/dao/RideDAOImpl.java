package com.volunteeride.dao;

import com.volunteeride.model.Ride;
import com.volunteeride.rest.resource.beans.RideSearchQueryCriteriaBean;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Ride> searchRides(RideSearchQueryCriteriaBean rideSearchCriteria) {

        Pageable pageable = new PageRequest(rideSearchCriteria.getPage(), rideSearchCriteria.getSize());

        Query searchQuery = new Query();
        searchQuery.with(pageable);

        if(rideSearchCriteria.getCenterId() != null){
            searchQuery.addCriteria(Criteria.where("centerId").is(rideSearchCriteria.getCenterId()));
        }

        if(CollectionUtils.isNotEmpty(rideSearchCriteria.getRideSeekerIds())){
            searchQuery.addCriteria(Criteria.where("rideSeekerIds").in(rideSearchCriteria.getRideSeekerIds()));
        }

        if(rideSearchCriteria.getStatus() != null){
            searchQuery.addCriteria(Criteria.where("status").is(rideSearchCriteria.getStatus()));
        }

        long totalRecords = mongoTemplate.count(searchQuery, Ride.class);

        List<Ride> resultRides = mongoTemplate.find(searchQuery, Ride.class);

        return new PageImpl(resultRides, pageable, totalRecords);
    }
}
