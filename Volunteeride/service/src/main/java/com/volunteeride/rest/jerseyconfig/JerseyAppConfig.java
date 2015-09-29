package com.volunteeride.rest.jerseyconfig;

import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Named;
import javax.ws.rs.ApplicationPath;

/**
 *
 * This class is used to register the rest endpoints for Volunteeride Application
 *
 * Created by ayazlakdawala on 9/18/2015.
 */
@Named
@ApplicationPath("/volunteeride")
public class JerseyAppConfig extends ResourceConfig {

    public JerseyAppConfig() {

        //Register your rest endpoints here
        packages("com.volunteeride.rest");
    }

}
