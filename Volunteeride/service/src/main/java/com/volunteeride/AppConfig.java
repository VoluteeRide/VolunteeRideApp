package com.volunteeride;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * This class represents spring configuration for Volunteeride App
 *
 * Created by ayazlakdawala on 9/17/2015.
 */
@SpringBootApplication
public class AppConfig extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

}
