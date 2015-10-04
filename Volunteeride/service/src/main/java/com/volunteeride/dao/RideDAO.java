package com.volunteeride.dao;

import com.volunteeride.model.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * This class represents Data Access Object level operations for Ride Object.
 *
 * Created by ayazlakdawala on 9/1/15.
 */
public interface RideDAO extends MongoRepository<Ride, String> {

    List<Ride> findByCenterId(String centerId);

    Ride findByCenterIdAndId(String centerId, String rideId);

}
