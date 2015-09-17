package com.volunteeride.springconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * This class represents spring configuration for Volunteeride App
 *
 * Created by ayazlakdawala on 9/17/2015.
 */
@Configuration
@ComponentScan(basePackages = "com.volunteeride")
@Import({MongoDBConfig.class})
public class AppConfig {
}
