package com.volunteeride.springconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * This class represents spring configuration for Volunteeride App
 *
 * Created by ayazlakdawala on 9/17/2015.
 */
@Configuration
@Profile("prod")
@ComponentScan(basePackages = "com.volunteeride")
@Import({ProdDBConfig.class})
public class AppConfig {
}
