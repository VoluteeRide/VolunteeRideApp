package com.volunteeride.dao;

import com.volunteeride.model.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.inject.Named;

/**
 * This class represents Data Access Object level operations for Ride Object.
 *
 * Created by ayazlakdawala on 9/1/15.
 */
public interface RideDAO extends MongoRepository<Ride, String> {

}
