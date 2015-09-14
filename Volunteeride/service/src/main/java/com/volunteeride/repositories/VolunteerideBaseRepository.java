package com.volunteeride.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author ayazlakdawala
 * @version Titanium R9.7
 * @since 9/11/2015
 */
@NoRepositoryBean
public interface VolunteerideBaseRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {
}
