package com.volunteeride.dao;

import com.volunteeride.model.VolunteerideUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ayazlakdawala on 9/17/15.
 */
public interface UserDAO extends MongoRepository<VolunteerideUser, String> {

    VolunteerideUser findByUsername(String username);

    Long countByUsername(String username);
}
