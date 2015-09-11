package com.volunteeride.springconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * This class represents the spring configuration for the Volunteeride app.
 *
 * @author ayazlakdawala
 * @version Titanium R9.7
 * @since 9/11/2015
 */

@Configuration
@EnableMongoRepositories("com.volunteeride.repositories")
@ComponentScan(basePackages = "com.volunteeride")
public class VolunteerideAppConfig {
}
