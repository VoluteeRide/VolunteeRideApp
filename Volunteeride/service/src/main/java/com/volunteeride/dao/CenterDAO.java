package com.volunteeride.dao;

import com.volunteeride.model.Center;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.inject.Named;

/**
 * Created by ayazlakdawala on 9/17/15.
 */
public interface CenterDAO extends MongoRepository<Center, String>{
}
