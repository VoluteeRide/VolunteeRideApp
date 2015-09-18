package com.volunteeride.dao;

import com.volunteeride.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.inject.Named;

/**
 * Created by ayazlakdawala on 9/17/15.
 */
public interface UserDAO extends MongoRepository<User, String> {
}
