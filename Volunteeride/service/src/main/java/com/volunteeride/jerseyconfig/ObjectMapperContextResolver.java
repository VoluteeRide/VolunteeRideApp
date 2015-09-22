package com.volunteeride.jerseyconfig;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * This class injects the Object Mapper created by Spring Boot into
 * the Context resolver which then can be registed with Jersey.
 *
 * Created by ayazlakdawala on 9/21/2015.
 */
@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    @Inject
    private ObjectMapper mapper;

    @Override
    public ObjectMapper getContext(Class<?> type) {
        System.out.println("Jersey Object Mapper: " + mapper);
        return mapper;
    }
}
