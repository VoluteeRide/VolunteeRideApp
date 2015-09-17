package com.volunteeride.dao;

import com.volunteeride.model.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * This class represents Data Access Object level operations for Ride Object.
 *
 * Created by ayazlakdawala on 9/1/15.
 */
@RepositoryDefinition(domainClass = Ride.class, idClass = String.class)
public interface RideDAO extends MongoRepository {


}
